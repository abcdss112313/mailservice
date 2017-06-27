package com.email.controller;

import com.email.domain.Emails;
import com.email.service.MailService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;

/**
 * Created by windsorl on 2017/6/21.
 */
@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/template")
    @ResponseBody
    public String HttpSendMail(@RequestBody JSONObject parm) {
      try {
            Emails emails = new Emails();
            emails.setFrom(parm.getAsString("from"));
            emails.setPassword(parm.getAsString("password"));
            emails.setTo(parm.getAsString("to"));
            emails.setContent(parm.getAsString("content"));
            emails.setSubject(parm.getAsString("subject"));
            emails.setHours(parm.getAsNumber("hours").intValue());
            emails.setFromNikeName(parm.getAsString("nickname"));
            mailService.sendHtmlMail(emails);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

}
