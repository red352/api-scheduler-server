package com.yunxiao.service.data.support.cron;

import cn.hutool.cron.pattern.CronPatternBuilder;
import cn.hutool.cron.pattern.Part;
import com.yunxiao.service.data.support.ErrorCode;
import com.yunxiao.spring.reactive.model.result.BusinessException;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.util.regex.PatternSyntaxException;

/**
 * 0：每几秒
 * 1：每几分钟
 * 2：每几小时
 * 3：每天什么时候
 * <p>
 * cron的不记录时间，为了让cron表达式更有意义
 * <p>
 * 当使用"*:+"/"+"intValue"，intValue必须能被(当前单位最大值+1)整除
 *
 * @author LuoYunXiao
 * @since 2023/10/26 23:40
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CronModel {

    @NotNull
    private CronEnum type;

    @NotNull
    private LocalTime time;


    private static final String USER_TIP = "请尝试定义一个更有意义的时间,该单位值必须能被(当前单位最大值+1)整除";

    public String toCron() {
        return switch (type) {
            case SEC_IN_MIN -> {
                if (60 % time.getSecond() != 0) {
                    throw new BusinessException(ErrorCode.PARSE_CRON_ERROR, "秒数无意义，" + USER_TIP);
                }
                yield CronPatternBuilder.of()
                        .set(Part.SECOND, "*/" + time.getSecond())
                        .build();
            }
            case MIN_IN_HOUR -> {
                if (60 % time.getMinute() != 0) {
                    throw new BusinessException(ErrorCode.PARSE_CRON_ERROR, "分钟数无意义，" + USER_TIP);
                }
                yield CronPatternBuilder.of()
                        .set(Part.SECOND, String.valueOf(time.getSecond()))
                        .set(Part.MINUTE, "*/" + time.getMinute())
                        .build();
            }
            case HOUR_IN_DAY -> {
                if (24 % time.getHour() != 0) {
                    throw new BusinessException(ErrorCode.PARSE_CRON_ERROR, "小时数无意义，" + USER_TIP);
                }
                yield CronPatternBuilder.of()
                        .set(Part.SECOND, String.valueOf(time.getSecond()))
                        .set(Part.MINUTE, String.valueOf(time.getMinute()))
                        .set(Part.HOUR, "*/" + time.getHour())
                        .build();
            }
            case TIME_IN_DAY -> CronPatternBuilder.of()
                    .set(Part.SECOND, String.valueOf(time.getSecond()))
                    .set(Part.MINUTE, String.valueOf(time.getMinute()))
                    .set(Part.HOUR, String.valueOf(time.getHour()))
                    .build();
            case null -> throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        };
    }


    @Nullable
    public static CronModel of(String cron) {
        if (cron == null || cron.isBlank()) {
            return null;
        }
        String[] parts;
        try {
            parts = cron.split(" ", 6);
        } catch (PatternSyntaxException e) {
            log.error("cron表达式不合法,{}", cron);
            throw new BusinessException(ErrorCode.PARSE_CRON_ERROR);
        }

        for (int i = 0; i < 4; i++) {
            if (!parts[i].startsWith("*/")) {
                continue;
            }
            int timeUnitValue = Integer.parseInt(parts[i].replace("*/", ""));
            return switch (i) {
                case 0 -> new CronModel(CronEnum.SEC_IN_MIN, LocalTime.of(0, 0, timeUnitValue));
                case 1 ->
                        new CronModel(CronEnum.MIN_IN_HOUR, LocalTime.of(0, timeUnitValue, Integer.parseInt(parts[0])));
                case 2 ->
                        new CronModel(CronEnum.HOUR_IN_DAY, LocalTime.of(timeUnitValue, Integer.parseInt(parts[1]), Integer.parseInt(parts[0])));
                default -> throw new BusinessException(ErrorCode.PARSE_CRON_ERROR);
            };
        }
        return new CronModel(CronEnum.TIME_IN_DAY, LocalTime.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0])));
    }

}
