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

    OFF(0, "关闭"),
    ON(1, "开启"),;

    private final int status;
    private final String desc;
}
