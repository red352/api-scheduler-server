package com.yunxiao.service.scheduler;

import com.yunxiao.service.data.model.Api;
import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.model.support.json.JsonConvert;
import com.yunxiao.service.data.repository.ApiRepository;
import com.yunxiao.spring.core.rest.RequestObj;
import com.yunxiao.spring.core.rest.RestService;
import com.yunxiao.spring.core.rest.paser.ResponseParser;
import com.yunxiao.spring.core.rest.paser.StringResponseParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 12:40
 */
@Getter
@Component
@RequiredArgsConstructor
@Slf4j
public class RestManager {


    private final ApiRepository apiRepository;
    private final ExecutorService es;
    private final TriggerManager triggerManager;

    private final static RestService restService = new RestService(new RestTemplate());


    public void doRest(ApiTrigger apiTrigger) {
        // 1.首先查询api详情
        apiRepository.findById(apiTrigger.getApiId())
                .publishOn(Schedulers.fromExecutor(es))
                .doOnNext(api -> {
                    // 解析成请求对象
                    Map<String, List<String>> headers = null;
                    Map<String, List<String>> params = null;
                    URI uri = null;
                    try {
                        headers = JsonConvert.toBean(api.getHeaders()).getHeaders();
                        params = JsonConvert.toBean(api.getParams()).getParams();
                        uri = new URI(api.getUrl());
                    } catch (Exception e) {
                        log.error("解析api对象为请求对象失败,{}", api);
                    }
                    if (uri == null || !uri.isAbsolute() || uri.getHost() == null) {
                        log.error("url格式不正确,{}", api);
                        return;
                    }
                    if (params != null) {
                        uri = UriComponentsBuilder.fromUri(uri).queryParams(new MultiValueMapAdapter<>(params)).build().toUri();
                    }

                    RequestObj requestObj = RequestObj.builder()
                            .url(uri.toString())
                            .method(api.getMethod())
                            .body(api.getBody())
                            .headers(headers)
                            .build();
                    // 2.发送rest请求

                    ResponseParser<?> parser = execute(api, requestObj);
                    if (parser == null) return;
                    triggerManager.decide(apiTrigger, parser);

                    // 结束
                })
                .subscribe();
    }

    private ResponseParser<?> execute(Api api, RequestObj requestObj) {
        return switch (api.getResponseType()) {
            case 0 -> {
                // body为text类型
                StringResponseParser parser = null;
                try {
                    parser = restService.doStringRest(requestObj);
                    log.debug("接口请求成功,{}", api);
                } catch (Exception e) {
                    log.error("请求发送失败,{}", api);
                }
                yield parser;
            }
            default -> null;
        };
    }
}
