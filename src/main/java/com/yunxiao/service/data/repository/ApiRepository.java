package com.yunxiao.service.data.repository;

import com.yunxiao.service.data.model.Api;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author LuoYunXiao
 * @since 2023/10/20 23:11
 */
public interface ApiRepository extends R2dbcRepository<Api, Integer> {

    Flux<Api> searchApiByNameContainingAndUrlContainingAndCreateTimeIsBetween(Pageable pageable, String name, String url, LocalDateTime startTime, LocalDateTime endTime);

    Mono<Long> countApiByNameContainingAndUrlContainingAndCreateTimeIsBetween(String name, String url, LocalDateTime startTime, LocalDateTime endTime);

}
