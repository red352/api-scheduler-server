package com.yunxiao.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 13:54
 */
public class JavaTest {

    private final ExecutorService es = Executors.newFixedThreadPool(5);



    @Test
    void test5() {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));

    }


    @AfterEach
    void lock() throws InterruptedException {
        synchronized (JavaTest.class) {
            JavaTest.class.wait();
        }
    }
}
