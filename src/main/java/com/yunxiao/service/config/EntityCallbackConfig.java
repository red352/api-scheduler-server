package com.yunxiao.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import reactor.core.publisher.Mono;

/**
 * @author LuoYunXiao
 * @since 2023/12/17 12:35
 */
@Configuration
public class EntityCallbackConfig {

    @Bean
    ReactiveAuditorAware<String> reactiveAuditorAware() {
        return () -> Mono.just("admin");
    }


}
