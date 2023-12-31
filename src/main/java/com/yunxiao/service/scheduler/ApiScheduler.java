package com.yunxiao.service.scheduler;

import com.yunxiao.service.data.Status;
import com.yunxiao.service.data.model.ApiTrigger;
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
    private final RestManager restManager;

    public void schedule(ApiTrigger apiTrigger) {
        if (Status.OFF.getStatus() == apiTrigger.getStatus()) {
            log.debug("触发器状态置为OFF,结束添加到调度器");
            cancel(apiTrigger.getId());
            return;
        }

        schedulerManager.schedule(
                apiTrigger.getId().toString(),
                new CronTask(() -> restManager.doRest(apiTrigger), apiTrigger.getCron()));
        log.debug("添加触发器,{}", apiTrigger);
    }


    public void cancel(int triggerId) {
        log.debug("取消触发器,id:{}", triggerId);
        schedulerManager.cancel(String.valueOf(triggerId));
    }

}
