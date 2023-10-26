package com.yunxiao.service.api.controller;

import com.yunxiao.service.data.model.Api;
import com.yunxiao.service.data.model.modify.ModApi;
import com.yunxiao.service.data.model.query.QueryApi;
import com.yunxiao.service.data.validation.group.SaveGroup;
import com.yunxiao.service.data.validation.group.UpdateGroup;
import com.yunxiao.service.data.repository.ApiRepository;
import com.yunxiao.spring.reactive.model.result.RequestPage;
import com.yunxiao.spring.reactive.model.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 19:44
 */
@RestController
@RequestMapping("/api/v1/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiRepository apiRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @PostMapping("/list")
    @Operation(summary = "获取api列表")
    Mono<Result<Page<Api>>> listApi(@RequestBody @Validated QueryApi queryApi) {
        List<Criteria> criteriaList = new ArrayList<>();
        if (queryApi.name() != null && !queryApi.name().isBlank()) {
            Criteria name = Criteria.where("name").like(queryApi.name() + "%");
            criteriaList.add(name);
        }
        if (queryApi.url() != null && !queryApi.url().isBlank()) {
            Criteria url = Criteria.where("url").like(queryApi.url() + "%");
            criteriaList.add(url);
        }
        if (queryApi.startTime() != null && queryApi.endTime() != null) {
            Criteria time = Criteria.where("createTime").between(queryApi.startTime(), queryApi.endTime());
            criteriaList.add(time);
        }
        Criteria criteria = Criteria.from(criteriaList);
        RequestPage page = queryApi.requestPage();
        return r2dbcEntityTemplate.select(Query.query(criteria).offset((long) (page.pageNum() - 1) * page.pageSize()).limit(page.pageSize()), Api.class)
                .collectList()
                .zipWith(r2dbcEntityTemplate.count(Query.query(criteria), Api.class))
                .map(tuple -> Result.ofPage(new PageImpl<>(tuple.getT1(), page.convert(), tuple.getT2())).ok());
    }

    @PostMapping("/save")
    @Operation(summary = "添加api")
    Mono<Result<Api>> saveApi(@RequestBody @Validated(SaveGroup.class) ModApi modApi) {
        return apiRepository.save(modApi.convert()).map(Result::ok);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除api", description = "")
    Mono<Result<Void>> deleteApi(@RequestBody @Validated(UpdateGroup.class) ModApi modApi) {
        return apiRepository.deleteById(modApi.getId()).thenReturn(Result.ok());
    }


    @PostMapping("/update")
    @Operation(summary = "更新api")
    Mono<Result<Void>> updateApi(@RequestBody @Validated(UpdateGroup.class) ModApi modApi) {
        Api api = modApi.convert();
        return r2dbcEntityTemplate.update(
                Query.query(Criteria.where("id").is(api.getId())),
                Update.update("name", api.getName())
                        .set("url", api.getUrl())
                        .set("method", api.getMethod())
                        .set("params", api.getParams())
                        .set("body", api.getBody())
                        .set("headers", api.getHeaders())
                        .set("response_type", api.getResponseType()),
                Api.class
        ).thenReturn(Result.ok());
    }
}
