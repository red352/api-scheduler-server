package com.yunxiao.service.config;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/10/28 15:49
 */
@Configuration
public class JacksonCustomConfig implements InitializingBean {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建一个SimpleModule，并在其中注册对应的类型
        SimpleModule module = new SimpleModule();
        module.addKeyDeserializer(List.class, new KeyDeserializer() {
            @Override
            public List<String> deserializeKey(String s, DeserializationContext deserializationContext) {
                return JSONUtil.toBean(s, new TypeReference<>() {
                }, false);
            }
        });
        objectMapper.registerModule(module);
    }
}
