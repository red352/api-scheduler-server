package com.yunxiao.service.api.controller;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.model.vo.ApiTriggerVo;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.spring.reactive.model.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 23:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apiTrigger")
public class ApiTriggerController {

    private final ApiTriggerRepository triggerRepository;

    @Operation(summary = "获取触发器列表")
    @PostMapping("list")
    Mono<Result<List<ApiTriggerVo>>> listApiTrigger() {
        return triggerRepository.findAll()
                .map(ApiTriggerVo::of)
                .collectList()
                .map(Result::ok);
    }

    @Operation(summary = "添加触发器")
    @PostMapping("add")
    Mono<Result<ApiTrigger>> saveApiTrigger(@RequestBody ApiTriggerVo vo) {
        return triggerRepository.save(vo.toEntity()).map(Result::ok);
    }
}
