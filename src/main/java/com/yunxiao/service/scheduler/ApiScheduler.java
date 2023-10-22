package com.yunxiao.service.scheduler;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.executor.RestExecutor;
import com.yunxiao.spring.core.scheduler.TaskSchedulerManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:20
 */
@Component
@RequiredArgsConstructor
@Getter
@Slf4j
public class ApiScheduler {

    private final TaskSchedulerManager schedulerManager;
    private final RestExecutor restExecutor;

    public void schedule(ApiTrigger apiTrigger) {
        schedulerManager.schedule(
                apiTrigger.getId().toString(),
                new CronTask(() -> restExecutor.doRest(apiTrigger), apiTrigger.getCron()));
    }

}
