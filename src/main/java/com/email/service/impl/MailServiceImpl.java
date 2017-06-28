package com.email.service.impl;

import com.email.domain.Emails;
import com.email.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.minidev.json.JSONObject;
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
    public void sendHtmlMail(Emails emails) throws MessagingException {
        MimeMessage message = getMailSender(emails.getFrom(), emails.getPassword()).createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(emails.getFrom());
        String receiver = emails.getTo();
        String receivers[] = receiver.split(";");
        helper.setTo(receivers);
        helper.setSubject(emails.getSubject());
        Map model = new HashMap<>();
        model.put("userFrom", emails.getFromNikeName());
        model.put("subject",emails.getSubject());
        model.put("hours",emails.getHours());
        model.put("content",emails.getContent());
        try {
            Template template = freeMarkerConfigurer.getConfiguration().
                    getTemplate("emailTemplate.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMailSender(emails.getFrom(), emails.getPassword()).send(message);
    }

    @Override
    public void sendHtmlMailForQuery(Emails emails) throws MessagingException {
        MimeMessage message = getMailSender(emails.getFrom(), emails.getPassword()).createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(emails.getFrom());
        helper.setTo(emails.getTo());
        helper.setSubject(emails.getSubject());
        Map model = new HashMap<>();
        model.put("userFrom", emails.getFromNikeName());
        model.put("subject",emails.getSubject());
      //model.put("hours",emails.getHours());
        model.put("nickName",emails.getFromNikeName());
        model.put("askForLeave",emails.getAskforleave());
        model.put("overTimeHour",emails.getOvertimehour());
        model.put("restHours",emails.getRestHour());
        try {
            Template template = freeMarkerConfigurer.getConfiguration().
                    getTemplate("emailTemplateforquery.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMailSender(emails.getFrom(), emails.getPassword()).send(message);
    }
}
