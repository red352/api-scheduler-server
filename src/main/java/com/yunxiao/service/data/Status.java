package com.yunxiao.service.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:26
 */
@Getter
@AllArgsConstructor
public enum Status {

    ON(1, "开启"),
    OFF(0, "关闭");

    private final int status;
    private final String desc;
}
