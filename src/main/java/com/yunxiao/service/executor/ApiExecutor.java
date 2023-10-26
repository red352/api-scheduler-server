package com.yunxiao.service.executor;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.spring.core.rest.paser.ResponseParser;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 19:47
 */
public interface ApiExecutor {

    void execute(ApiTrigger apiTrigger, ResponseParser<?> parser);

}
