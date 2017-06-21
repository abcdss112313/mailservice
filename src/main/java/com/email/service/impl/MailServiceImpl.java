package com.email.service.impl;

import com.email.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by windsorl on 2017/6/21.
 */
@Service
public class MailServiceImpl implements MailService {


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String isAuth;

    @Value("${mail.smtp.timeout}")
    private String outTime;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  //自动注入

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public JavaMailSenderImpl getMailSender(String account, String password) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(account);
        javaMailSender.setPassword(password);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", isAuth);
        properties.put("mail.smtp.timeout", outTime);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }


    @Override
    public void sendHtmlMail(String from, String password, String to, String subject, String content) throws MessagingException {
        MimeMessage message = getMailSender(from, password).createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(from);
        String receiver = to;
        String receivers[] = receiver.split(";");
        helper.setTo(to);
        helper.setSubject(subject);
        Map model = new HashMap<>();
        model.put("id", content);
        try {
            Template template = freeMarkerConfigurer.getConfiguration().
                    getTemplate("emailTemplate.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getMailSender(from, password).send(message);

    }
}
