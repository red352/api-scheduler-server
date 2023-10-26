package com.yunxiao.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author LuoYunXiao
 * @since 2023/10/26 21:24
 */
@SpringBootTest
public class CacheTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Cache cache;

    @Test
    void test1() {
        System.out.println(cacheManager.getCacheNames());
        Cache cache1 = cacheManager.getCache("api");
        System.out.println(cache);
        System.out.println(cache1);
    }
}
