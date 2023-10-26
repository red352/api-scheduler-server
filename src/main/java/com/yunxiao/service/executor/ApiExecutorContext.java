package com.yunxiao.service.executor;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.spring.core.rest.paser.ResponseParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 19:43
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ApiExecutorContext implements ApiExecutor {

    private final MailExecutor mailExecutor;
    private final ExecutorService es;

    @Override
    public void execute(ApiTrigger apiTrigger, ResponseParser<?> parser) {
        log.debug("触发成功，{}", apiTrigger);
        if (apiTrigger.getExecType() == null || apiTrigger.getExecType().isBlank()) {
            return;
        }
        String[] types = apiTrigger.getExecType().split(",");
        for (String type : types) {
            try {
                int i = Integer.parseInt(type);
                doActually(i, apiTrigger, parser);
            } catch (NumberFormatException e) {
                log.error("不存在此执行类型,{}", apiTrigger);
            }
        }

    }

    private void doActually(int i, ApiTrigger apiTrigger, ResponseParser<?> parser) {
        switch (i) {
            // 邮件
            case 0 -> es.execute(() -> mailExecutor.execute(apiTrigger, parser));
            default -> log.error("不存在此执行类型,{}", apiTrigger);
        }
    }
}
