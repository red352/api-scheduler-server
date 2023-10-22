package com.yunxiao.service;


import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.service.executor.MailExecutor;
import com.yunxiao.spring.reactive.utils.TestReactor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;


@SpringBootTest
public class AppTest{
    @Autowired
    private ApiTriggerRepository apiTriggerRepository;
    @Test
    void test1() {
        apiTriggerRepository.findAll().toStream().forEach(System.out::println);
        Mono<ApiTrigger> mono = apiTriggerRepository.findById(2);
        System.out.println(TestReactor.create(mono).collectToOne());
    }

    @Test
    void testMail(@Autowired MailExecutor mailExecutor) {
        mailExecutor.sendMail("1829462342@qq.com","测试发送邮件","");
    }

}
