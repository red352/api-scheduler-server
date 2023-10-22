package com.yunxiao.service.data.repository;

import com.yunxiao.service.data.model.ApiTrigger;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author LuoYunXiao
 * @since 2023/10/20 23:21
 */
public interface ApiTriggerRepository extends R2dbcRepository<ApiTrigger, Integer> {

    Flux<ApiTrigger> findByStatusEquals(Integer status);

    @Modifying
    @Query("UPDATE api_trigger SET last_exec = :lastExec WHERE id = :id")
    Mono<Void> updateLastExecById(Integer id, LocalDateTime lastExec);

}
