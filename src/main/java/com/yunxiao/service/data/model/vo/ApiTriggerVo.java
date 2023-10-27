package com.yunxiao.service.data.model.vo;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.support.cron.CronModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 * @author LuoYunXiao
 * @since 2023/10/27 23:27
 */
@Getter
@Setter
@ToString
public class ApiTriggerVo extends ApiTrigger {

    private CronModel cronModel;

    public static ApiTriggerVo of(ApiTrigger apiTrigger) {

        ApiTriggerVo vo = new ApiTriggerVo();
        BeanUtils.copyProperties(apiTrigger, vo);
        vo.setCronModel(CronModel.of(apiTrigger.getCron()));
        return vo;
    }

    public ApiTrigger toEntity(String... ignoreProperties) {
        if (ignoreProperties == null || ignoreProperties.length == 0) {
            ignoreProperties = new String[]{"id", "lastExec", "createTime"};
        }
        ApiTrigger apiTrigger = new ApiTrigger();
        BeanUtils.copyProperties(this, apiTrigger, ignoreProperties);
        apiTrigger.setCron(cronModel.toCron());
        return apiTrigger;
    }

}
