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
    public Object HttpSendMail(@RequestBody Emails mail) {
        JSONObject json = new JSONObject();
        try {
            mailService.sendHtmlMail(mail.getFrom(), mail.getPassword(),
                    mail.getTo(), mail.getSubject(), mail.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return json.put("message", "fail");
        }
        json.put("message", "success");
        return json;
    }
}
