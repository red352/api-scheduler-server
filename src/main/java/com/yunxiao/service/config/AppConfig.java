package com.yunxiao.service.config;

import com.yunxiao.spring.core.scheduler.TaskSchedulerManager;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:22
 */
@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    TaskSchedulerManager taskSchedulerManager() {
        return new TaskSchedulerManager(new ThreadPoolTaskScheduler());
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    Cache cache(CacheManager cacheManager) {
        return cacheManager.getCache("api");
    }

}
