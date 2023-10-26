package com.yunxiao.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.cron.pattern.CronPatternBuilder;
import cn.hutool.cron.pattern.Part;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunxiao.service.data.model.support.json.JsonModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 13:54
 */
public class JavaTest {

    private final ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();


    @Test
    void test() {

        es.execute(() -> {
            es.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("内层任务");
            });
            System.out.println("外层任务");

        });

    }

    @Test
    void test1() throws JsonProcessingException {
        Map<List<String>, String> data = Map.of(List.of("data", "id"), "9999");
        JsonModel jsonModel = new JsonModel();
        jsonModel.setExpectData(data);
        System.out.println("原来的数据" + jsonModel);
        String string = JSONUtil.parse(jsonModel).toString();
        System.out.println("解析后的数据" + string);
        JsonModel data1 = JSONUtil.toBean(string, JsonModel.class);
        System.out.println("还原的数据" + data1);
    }

    @Test
    void test2() throws InterruptedException {
        List<Map<List<String>, String>> data = List.of(Map.of(List.of("data", "id"), "9999"));
        System.out.println("原来的数据" + data);
        String string = JSONUtil.parse(data).toString();
        System.out.println("解析后的数据" + string);
        List<Map<List<String>, String>> data1 = JSONUtil.toBean(string, new TypeReference<>() {
        }, true);
        System.out.println("还原的数据" + data1);
    }

    @Test
    void test3() throws InterruptedException {
        JsonModel sad = JSONUtil.toBean("{sd:sd}", JsonModel.class);
        System.out.println(sad);
    }

    @Test
    void test4() {
        String build = CronPatternBuilder.of().set(Part.SECOND, "*/10").build();
        System.out.println(build);
        String jsonStr = JSONUtil.toJsonStr(JsonModel.builder()
//                .headers(Map.of("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0")))
//                .expectData(Map.of(List.of("data", "mid"), "1366330988"))
                .params(Map.of("mid", List.of("1366330988")))
                .build());
        System.out.println(jsonStr);
        System.out.println(JSONUtil.toBean(jsonStr, JsonModel.class));

    }

    @Test
    void test5() {
        String s = "";
        String[] split = s.split(",");
        System.out.println(Arrays.toString(split));

    }


//    @AfterEach
//    void lock() throws InterruptedException {
//        synchronized (JavaTest.class) {
//            JavaTest.class.wait();
//        }
//    }
}
