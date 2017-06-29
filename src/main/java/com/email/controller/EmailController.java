package com.email.controller;

import com.email.domain.Emails;
import com.email.service.MailService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Created by windsorl on 2017/6/21.
 */
@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private MailService mailService;
    @Value("${system.email.acc}")
    private String sysEmailAcc;
    @Value("${system.email.pwd}")
    private String sysEmailPwd;

    @RequestMapping("/template")
    @ResponseBody
    public String HttpSendMail(@RequestBody JSONObject parm) {
        try {
            Emails emails = new Emails();
            emails.setFrom(parm.getAsString("from"));
            emails.setPassword(parm.getAsString("password"));
            emails.setTo(parm.getAsString("to"));
            emails.setContent(parm.getAsString("content"));
            emails.setSubject(parm.getAsString("subject")) ;
          //emails.setOvertimehour(parm.getAsNumber("overtimehour").intValue());
          //emails.setAskforleave(parm.getAsNumber("askforleave").intValue());
            emails.setFromNikeName(parm.getAsString("nickname"));
            emails.setHours(parm.getAsNumber("hours").intValue());
            emails.setApproveStatus(parm.getAsString("approveStatus"));
          //emails.setRestHour(parm.getAsNumber("restHours").intValue());

            mailService.sendHtmlMail(emails);
            //系统收到邮件转发给用户
            if (!"".equals(parm.getAsString("system"))&& parm.getAsString("system") !=null ) {
                Emails systememails = new Emails();
                systememails.setFrom(sysEmailAcc);
                systememails.setPassword(sysEmailPwd);
                systememails.setTo(emails.getTo() + ";" + emails.getFrom());
                systememails.setContent(emails.getContent());
                systememails.setFromNikeName("系统邮件");
                systememails.setAskforleave(parm.getAsNumber("askforleave").intValue());
                systememails.setOvertimehour(parm.getAsNumber("overtimehour").intValue());
                systememails.setRestHour(parm.getAsNumber("restHours").intValue());
                systememails.setSubject("系统查询回复");
                systememails.setHours(emails.getHours());
                mailService.sendHtmlMailForQuery(systememails);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String HttpSendMailforQuery(@RequestBody JSONObject parm) {
        try {
            //首先是用户发送给系统邮件
            Emails emails = new Emails();
            emails.setFrom(parm.getAsString("from"));
            emails.setPassword(parm.getAsString("password"));
            emails.setTo(sysEmailAcc);
            emails.setContent(parm.getAsString("content"));
            emails.setSubject(parm.getAsString("subject"));
            emails.setOvertimehour(parm.getAsNumber("overtimehour").intValue());
            emails.setAskforleave(parm.getAsNumber("askforleave").intValue());
            emails.setFromNikeName(parm.getAsString("nickname"));
            emails.setRestHour(parm.getAsNumber("restHours").intValue());
            emails.setMailType("query");
            mailService.sendHtmlMail(emails);

            //系统发送给用户回复
            Emails systememails = new Emails();
            systememails.setFrom(sysEmailAcc);
            systememails.setPassword(sysEmailPwd);
            systememails.setTo(emails.getFrom());
            systememails.setContent(emails.getContent());
            systememails.setFromNikeName("系统邮件");
            systememails.setAskforleave(parm.getAsNumber("askforleave").intValue());
            systememails.setOvertimehour(parm.getAsNumber("overtimehour").intValue());
            systememails.setRestHour(parm.getAsNumber("restHours").intValue());
            systememails.setSubject("系统查询回复");
            systememails.setHours(emails.getHours());
            mailService.sendHtmlMailForQuery(systememails);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

}
