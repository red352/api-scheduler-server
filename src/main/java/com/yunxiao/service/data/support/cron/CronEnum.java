package com.yunxiao.service.data.support.cron;

/**
 * @author LuoYunXiao
 * @since 2023/10/27 21:29
 */
public enum CronEnum {

    SEC_IN_MIN,
    MIN_IN_HOUR,
    HOUR_IN_DAY,
    TIME_IN_DAY;

    public static CronEnum valueOf(int i) {
        return values()[i];
    }

}
