package com.yunxiao.service.executor;

import com.yunxiao.service.data.model.ApiTrigger;
import com.yunxiao.service.data.repository.ApiTriggerRepository;
import com.yunxiao.spring.core.rest.paser.ResponseParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 16:33
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MailExecutor implements InitializingBean, ApiExecutor {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;
    private final ApiTriggerRepository apiTriggerRepository;

    private String from;
    private static final String fromFormatter = "%s" + "<%s>";

    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(Optional.ofNullable(subject).orElse(mailProperties.getProperties().get("default-subject")));
        message.setText(Optional.ofNullable(content).orElse("您调度的任务已被触发，由于未设置邮件内容，这是默认内容，请及时添加邮件模板！"));
        javaMailSender.send(message);
    }

    @Override
    public void execute(ApiTrigger apiTrigger, ResponseParser<?> parser) {
        // 尝试发送邮件
        log.debug("触发成功,发送邮件");
        Mono.fromRunnable(() -> sendMail(apiTrigger.getExpectedMail(), apiTrigger.getExpectedSubject(), apiTrigger.getExpectedText()))
                .doOnError(throwable -> log.error("发送邮件失败,{}", apiTrigger))
                // 更新执行时间
                .onErrorStop()
                .then(apiTriggerRepository.updateLastExecById(apiTrigger.getId(), LocalDateTime.now()))
                .subscribe();
    }

    @Override
    public void afterPropertiesSet() {
        String nick = mailProperties.getProperties().get("nick");
        String username = mailProperties.getUsername();
        if (nick != null) {
            this.from = fromFormatter.formatted(nick, username);
            return;
        }
        this.from = username;


    }

}
