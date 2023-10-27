package com.yunxiao.service.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:17
 */
@Getter
@AllArgsConstructor
public enum TriggerType {
    ANYTIME_NOT_TRIGGER(-1, "任何时候不触发"),
    ANYTIME_TRIGGER(0, "任何时候触发"),
    HTTP_STATUS_TRIGGER(1, "根据Http状态码触发"),
    JSON_VALUE_TRIGGER(2, "根据Json指定值触发");

    private final int type;
    private final String desc;


    public static TriggerType valueOf(int type) {
        for (TriggerType triggerType : values()) {
            if (type == triggerType.getType()) {
                return triggerType;
            }
        }
        return null;
    }

}
