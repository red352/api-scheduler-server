package com.yunxiao.service.data.model.modify;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.yunxiao.service.data.model.Api;
import com.yunxiao.service.data.support.json.JsonConvert;
import com.yunxiao.service.data.support.json.JsonModel;
import com.yunxiao.service.data.validation.IsInArrayValidation;
import com.yunxiao.service.data.validation.group.SaveGroup;
import com.yunxiao.service.data.validation.group.UpdateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 15:43
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModApi {
    @NotNull(groups = UpdateGroup.class)
    @Null(groups = SaveGroup.class)
    private Integer id;

    @NotBlank(groups = {SaveGroup.class, UpdateGroup.class})
    private String name;

    @NotBlank(groups = {SaveGroup.class, UpdateGroup.class})
    private String url;

    @IsInArrayValidation(
            value = {"GET", "POST", "PUT", "DELETE"},
            message = "不支持的请求方法",
            groups = {SaveGroup.class, UpdateGroup.class})
    private String method;

    @NotNull(groups = {SaveGroup.class, UpdateGroup.class})
    private Integer responseType;

    private String params;

    private String body;

    private String headers;


    private static final TypeReference<Map<String, List<String>>> typeReference = new TypeReference<>() {
    };

    public Api convert() {
        var paramsMap = JSONUtil.toBean(params, typeReference, false);
        var headersMap = JSONUtil.toBean(headers, typeReference, false);

        String paramsStr = paramsMap == null ? null : JsonConvert.toJsonStr(JsonModel.builder().params(paramsMap).build());
        String headersStr = headersMap == null ? null : JsonConvert.toJsonStr(JsonModel.builder().headers(headersMap).build());
        return Api.builder()
                .id(id)
                .name(name)
                .url(url)
                .method(method)
                .params(paramsStr)
                .body(body)
                .headers(headersStr)
                .responseType(responseType)
                .build();
    }
}
