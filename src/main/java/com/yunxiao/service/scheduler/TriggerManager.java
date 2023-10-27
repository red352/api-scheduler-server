package com.yunxiao.service.scheduler;

import cn.hutool.json.JSON;
import com.yunxiao.service.data.TriggerType;
import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.support.json.JsonConvert;
import com.yunxiao.service.executor.ApiExecutorContext;
import com.yunxiao.spring.core.rest.paser.ResponseParser;
import com.yunxiao.spring.core.rest.paser.StringResponseParser;
import com.yunxiao.spring.core.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 14:28
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TriggerManager {

    private final ApiExecutorContext apiExecutorContext;
    private final Cache cache;

    public void decide(@NonNull ApiTrigger apiTrigger, @NonNull ResponseParser<?> parser) {
        switch (TriggerType.valueOf(apiTrigger.getTriggerType())) {
            case ANYTIME_NOT_TRIGGER -> {
                // 任何时候不触发
            }
            case ANYTIME_TRIGGER -> {
                // 任何时候触发
                apiExecutorContext.execute(apiTrigger, parser);
            }
            case HTTP_STATUS_TRIGGER -> {
                // 根据Http状态码触发
                if (Objects.equals(apiTrigger.getExpectData(), String.valueOf(parser.getHttpStatus()))) {
                    apiExecutorContext.execute(apiTrigger, parser);
                }
            }
            case JSON_VALUE_TRIGGER -> {
                // 根据Json指定值触发
                Map<List<String>, String> data = null;
                try {
                    data = JsonConvert.toBean(apiTrigger.getExpectData()).getExpectData();
                } catch (Exception e) {
                    log.error("解析触发器预期值失败,{}", apiTrigger);
                }
                JSON resJson = null;
                if (parser instanceof StringResponseParser responseParser) {
                    resJson = responseParser.getJson();
                    log.debug("请求返回结果是,{}", resJson);
                }
                if (resJson == null || data == null || data.isEmpty()) {
                    cache.evictIfPresent(apiTrigger.getId());
                    return;
                }
                // 检查值是否都和预期满足
                for (Map.Entry<List<String>, String> entry : data.entrySet()) {
                    if (!Objects.equals(JsonUtils.getJsonValue(resJson, entry.getKey()), entry.getValue())) {
                        cache.evictIfPresent(apiTrigger.getId());
                        return;
                    }
                }
                // 满足条件，通过
                log.debug("满足条件");
                // 如果缓存过，退出
                if (cache.get(apiTrigger.getId()) != null) {
                    return;
                }
                cache.put(apiTrigger.getId(), "pass");
                apiExecutorContext.execute(apiTrigger, parser);
            }
            case null, default -> log.error("触发类型不存在,{}", apiTrigger);
        }
    }


}
