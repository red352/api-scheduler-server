package com.yunxiao.service.data.model.support.json;

import cn.hutool.json.JSONUtil;
import org.springframework.lang.NonNull;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 16:32
 */
public class JsonConvert {

    public static String toJsonStr(@NonNull JsonModel jsonModel) {
        return JSONUtil.toJsonStr(jsonModel);
    }

    public static JsonModel toBean(String jsonStr) {
        JsonModel model = JSONUtil.toBean(jsonStr, JsonModel.class);
        return model == null ? new JsonModel() : model;
    }
}
