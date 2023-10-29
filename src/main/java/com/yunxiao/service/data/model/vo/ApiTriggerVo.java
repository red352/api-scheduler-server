package com.yunxiao.service.data.model.vo;

import com.yunxiao.service.data.model.Api;
import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.support.cron.CronModel;
import com.yunxiao.service.data.support.json.JsonConvert;
import com.yunxiao.service.data.support.json.JsonModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/27 23:27
 */
@Getter
@Setter
@ToString
public class ApiTriggerVo extends ApiTrigger {

    private CronModel cronModel;

    private Map<List<String>, String> expectDataCopy;

    private Api api;

    public static ApiTriggerVo of(ApiTrigger apiTrigger) {

        ApiTriggerVo vo = new ApiTriggerVo();
        BeanUtils.copyProperties(apiTrigger, vo);
        vo.setCronModel(CronModel.of(apiTrigger.getCron()));
        vo.setExpectDataCopy(JsonConvert.toBean(apiTrigger.getExpectData()).getExpectData());
        return vo;
    }

    public ApiTrigger toEntity(String... ignoreProperties) {
        if (ignoreProperties == null || ignoreProperties.length == 0) {
            ignoreProperties = new String[]{"id", "userId", "lastExec", "createTime"};
        }
        ApiTrigger apiTrigger = new ApiTrigger();
        BeanUtils.copyProperties(this, apiTrigger, ignoreProperties);
        if (cronModel != null) {
            apiTrigger.setCron(cronModel.toCron());
        }
        if (expectDataCopy == null || expectDataCopy.isEmpty()) {
            return apiTrigger; // 不需要预期数据，直接返回
        }
        apiTrigger.setExpectData(JsonConvert.toJsonStr(JsonModel.builder().expectData(expectDataCopy).build()));
        return apiTrigger;
    }

}
