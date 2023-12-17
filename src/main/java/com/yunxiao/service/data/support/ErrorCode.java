package com.yunxiao.service.data.support;

import com.yunxiao.spring.core.protocol.CodeAble;
import lombok.AllArgsConstructor;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 18:00
 */
@AllArgsConstructor
public enum ErrorCode implements CodeAble {
    SYSTEM_ERROR(10000, "未知错误，请联系管理员修复"),
    REQUEST_PARAM_ERROR(10001, "请求参数错误"),
    //
    PARSE_CRON_ERROR(20000, "cron解析失败");

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
