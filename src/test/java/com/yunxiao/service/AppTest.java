package com.yunxiao.service;


import com.yunxiao.service.data.repository.ApiRepository;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.service.executor.MailExecutor;
import com.yunxiao.spring.core.protocol.BaseEntity;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;


@SpringBootTest
public class AppTest {
    @Autowired
    private ApiRepository apiRepository;
    @Autowired
    private ApiTriggerRepository apiTriggerRepository;
    @Autowired
    private DatabaseClient databaseClient;
    @Autowired
    private R2dbcEntityTemplate template;


    @Test
    void test2() {
        BaseEntity baseEntity = new BaseEntity();

//        baseEntity.setDeleted(0);
//
//        template.update(BaseEntity.class).inTable("base_entity")
//                .matching(Query.query(Criteria.where("deleted").not(1)))
//                .apply(Update.update("deleted", 1)).subscribe();

        template.insert(BaseEntity.class).into("base_entity").using(baseEntity).then().subscribe();
//        template.select(BaseEntity.class).first().subscribe(System.out::println);
    }

    @Test
    void testMail(@Autowired MailExecutor mailExecutor) {
        mailExecutor.sendMail("1829462342@qq.com", "测试发送邮件", "");
    }

    @SneakyThrows
    @AfterEach
    void clean() {
        synchronized (this) {
            this.wait();
        }
    }

}
