package com.yunxiao.service.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 16:33
 */
@Component
@RequiredArgsConstructor
public class MailExecutor implements InitializingBean {

    private final JavaMailSender javaMailSender;
    private final MailProperties mailProperties;

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
