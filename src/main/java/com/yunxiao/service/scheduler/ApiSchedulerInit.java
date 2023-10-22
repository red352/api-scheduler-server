package com.yunxiao.service.scheduler;

import com.yunxiao.service.data.Status;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:29
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ApiSchedulerInit implements CommandLineRunner {

    private final ApiTriggerRepository triggerRepository;
    private final ApiScheduler apiScheduler;

    @Override
    public void run(String... args) {
        // 启动时加载所有任务到调度器中
        triggerRepository.findByStatusEquals(Status.ON.getStatus())
                .doOnNext(apiScheduler::schedule)
                .count()
                .subscribe(count -> log.info("已找到{}个待调度的任务，已加入调度器", count));

    }
}
