package com.yunxiao.service.data.model.modify;

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

    @NotBlank(groups = {SaveGroup.class})
    private String name;

    @NotBlank(groups = {SaveGroup.class})
    private String url;

    @IsInArrayValidation(
            value = {"GET", "POST", "PUT", "DELETE"},
            message = "不支持的请求方法",
            groups = {SaveGroup.class})
    private String method;

    @NotNull(groups = {SaveGroup.class})
    private Integer responseType;

    private Map<String, List<String>> params;

    private String body;

    private Map<String, List<String>> headers;


    public Api convert() {

        String paramsStr = params == null ? null : JsonConvert.toJsonStr(JsonModel.builder().params(params).build());
        String headersStr = headers == null ? null : JsonConvert.toJsonStr(JsonModel.builder().headers(headers).build());
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
