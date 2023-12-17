package com.yunxiao.service.data.model.query;

import com.yunxiao.spring.core.protocol.RequestPage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 21:50
 */
public record QueryApi(
        @Schema(description = "接口名称")
        String name,
        @Schema(description = "接口地址")
        String url,
        @Schema(description = "创建时间-最小值")
        LocalDateTime startTime,
        @Schema(description = "创建时间-最大值")
        LocalDateTime endTime,
        @Valid
        RequestPage requestPage) {


}
