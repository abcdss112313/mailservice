package com.email.service;

import com.email.domain.Emails;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.util.List;

/**
 * Created by windsorl on 2017/6/21.
 */
public interface MailService {

    public JavaMailSenderImpl getMailSender(String account,String password);

    public void sendHtmlMail(Emails emails) throws MessagingException;

    public void sendHtmlMailForQuery(Emails emails) throws MessagingException;

}
