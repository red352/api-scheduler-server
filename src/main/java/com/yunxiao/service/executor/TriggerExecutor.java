package com.yunxiao.service.executor;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.model.support.json.JsonConvert;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.spring.core.rest.paser.ResponseParser;
import com.yunxiao.spring.core.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
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
public class TriggerExecutor {

    private final ApiTriggerRepository apiTriggerRepository;
    private final MailExecutor mailExecutor;

    public void decide(@NonNull ApiTrigger apiTrigger, @NonNull ResponseParser<?> parser) {
        switch (apiTrigger.getTriggerType()) {
            case -1 -> {
                // 任何时候不触发
            }
            case 0 -> {
                // 任何时候触发
                execute(apiTrigger, parser);
            }
            case 1 -> {
                // 根据Http状态码触发
                if (Objects.equals(apiTrigger.getExpectData(), String.valueOf(parser.getHttpStatus()))) {
                    execute(apiTrigger, parser);
                }
            }
            case 2 -> {
                // 根据Json指定值触发
                Map<List<String>, String> data = null;
                try {
                    data = JsonConvert.toBean(apiTrigger.getExpectData()).getExpectData();
                } catch (Exception e) {
                    log.error("解析触发器预期值失败,{}", apiTrigger);
                }
                Object body = parser.getResponseEntity().getBody();
                if (body == null || data == null || data.isEmpty()) {
                    return;
                }
                JSON resJson = JSONUtil.parse(body);
                // 检查值是否都和预期满足
                for (Map.Entry<List<String>, String> entry : data.entrySet()) {
                    if (!Objects.equals(JsonUtils.getJsonValue(resJson, entry.getKey()), entry.getValue())) {
                        return;
                    }
                }
                execute(apiTrigger, parser);
            }
            default -> log.error("触发类型不存在,{}", apiTrigger);
        }
    }

    public void execute(ApiTrigger apiTrigger, ResponseParser<?> parser) {
        // 尝试发送邮件
        log.debug("触发成功,发送邮件");
        Mono.fromRunnable(() -> mailExecutor
                        .sendMail(apiTrigger.getExpectedMail(), apiTrigger.getExpectedSubject(), apiTrigger.getExpectedText()))
                .doOnError(throwable -> log.error("发送邮件失败,{}", apiTrigger))
                // 更新执行时间
                .onErrorStop()
                .then(apiTriggerRepository.updateLastExecById(apiTrigger.getId(), LocalDateTime.now()))
                .subscribe();
    }

}
