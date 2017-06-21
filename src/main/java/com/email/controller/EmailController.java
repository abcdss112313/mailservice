package com.email.controller;

import com.email.service.MailService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by windsorl on 2017/6/21.
 */
@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private MailService mailService;



    @RequestMapping("/template/{from}/{password}/{to}/{subject}/{content}")
    public String HttpSendMail(ModelMap modelMap, @PathVariable String from ,@PathVariable String password,@PathVariable String to,
                                 @PathVariable String subject,@PathVariable String content){
        try {
            mailService.sendHtmlMail(from,password,to,subject,content);
            modelMap.put("success","send mail success");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "sucess" ;
    }

}
