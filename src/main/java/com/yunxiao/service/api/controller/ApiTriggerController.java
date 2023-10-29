package com.yunxiao.service.api.controller;

import com.yunxiao.service.data.Status;
import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.model.vo.ApiTriggerVo;
import com.yunxiao.service.data.repository.ApiRepository;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.service.scheduler.ApiScheduler;
import com.yunxiao.spring.reactive.model.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 23:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apiTrigger")
@CrossOrigin
public class ApiTriggerController {

    private final ApiTriggerRepository triggerRepository;
    private final ApiRepository apiRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final ApiScheduler scheduler;

    // TODO: 这里应该根据用户id查询
    @Operation(summary = "获取触发器列表")
    @PostMapping("list")
    Mono<Result<List<ApiTriggerVo>>> listApiTrigger() {
        return triggerRepository.findAll()
                // 这里循环查询，为了减少查询次数可以使用批量查询
                .flatMap(apiTrigger -> {
                    ApiTriggerVo vo = ApiTriggerVo.of(apiTrigger);
                    Integer apiId = apiTrigger.getApiId();
                    if (apiId != null) {
                        return apiRepository.findById(apiId).doOnNext(vo::setApi).thenReturn(vo);
                    }
                    return Mono.just(vo);
                })
                .collectList()
                .map(Result::ok);
    }

    @Operation(summary = "添加触发器")
    @PostMapping("add")
    Mono<Result<ApiTrigger>> saveApiTrigger(@RequestBody @Validated ApiTriggerVo vo) {
        return triggerRepository.save(vo.toEntity()).map(Result::ok);
    }

    @Operation(summary = "更改触发器状态")
    @PutMapping("updateStatus")
    @Validated
    Mono<Result<Void>> updateApiTrigger(@RequestParam @NotNull Integer id, @RequestParam @NotNull Status status) {
        return triggerRepository.updateStatus(id, status.getStatus())
                .then(triggerRepository.findById(id))
                .doOnNext(scheduler::schedule)
                .thenReturn(Result.ok());
    }

    @Operation(summary = "修改触发器")
    @PostMapping("update")
    Mono<Result<Void>> updateApiTrigger(@RequestBody ApiTriggerVo vo) {
        ApiTrigger entity = vo.toEntity();
        Map<SqlIdentifier, Object> updateMap = new HashMap<>();
        if (entity.getTriggerType() != null) {
            updateMap.put(SqlIdentifier.quoted("trigger_type"), entity.getTriggerType());
        }
        if (entity.getCron() != null) {
            updateMap.put(SqlIdentifier.quoted("cron"), entity.getCron());
        }
        if (entity.getExpectData() != null) {
            updateMap.put(SqlIdentifier.quoted("expect_data"), entity.getExpectData());
        }
        if (entity.getExpectedMail() != null) {
            updateMap.put(SqlIdentifier.quoted("expected_mail"), entity.getExpectedMail());
        }
        if (entity.getExpectedSubject() != null) {
            updateMap.put(SqlIdentifier.quoted("expected_subject"), entity.getExpectedSubject());
        }
        if (entity.getExpectedText() != null) {
            updateMap.put(SqlIdentifier.quoted("expected_text"), entity.getExpectedText());
        }
        return r2dbcEntityTemplate.update(
                        Query.query(Criteria.where("id").is(vo.getId())),
                        Update.from(updateMap),
                        ApiTrigger.class)
                .map(count -> Result.ok());
    }
}
