package com.email;

import com.email.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailserviceApplicationTests {

	@Autowired
	private MailService mailService;

	@Autowired
	private TemplateEngine templateEngine;

//	@Test
//	public void sendSimpleMail() throws Exception {
//		mailService.sendSimpleMail("forevermother@126.com","forevermother@126.com","test","hello world");
//	}

	@Test
	public void sendTemplateMail() throws Exception{
		//Context context = new Context();
		//context.setVariable("id", "006");
		//String emailContent = templateEngine.process("emailTemplate", context);
		mailService.sendHtmlMail("forevermother@126.com","Liyangzhou115",
				"forevermother@126.com","testtest","helloworld"
				);
	}
}
