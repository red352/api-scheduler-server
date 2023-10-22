package com.yunxiao.service.data.model.support;

import com.yunxiao.spring.reactive.model.result.CodeAble;
import lombok.AllArgsConstructor;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 18:00
 */
@AllArgsConstructor
public enum ErrorCode implements CodeAble {
    REQUEST_PARAM_ERROR(10001, "请求参数错误"),
    ;

    private final int code;
    private final String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
