package com.yunxiao.service.api.controller;

import com.yunxiao.service.data.support.cron.CronModel;
import com.yunxiao.spring.reactive.model.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 23:29
 */
@RestController
@RequestMapping("/api/v1/apiTrigger")
public class ApiTriggerController {

    @GetMapping("list")
    Mono<Result<CronModel>> listApiTrigger(@RequestBody CronModel cronModel) {

        return Result.of(cronModel).toMono();
    }
}
