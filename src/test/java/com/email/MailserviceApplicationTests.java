package com.email;

import com.email.domain.Emails;
import com.email.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;


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
		Emails emails = new Emails();
		emails.setFrom("forevermother@126.com");
		emails.setPassword("Liyangzhou115");
		emails.setTo("182480610@qq.com");
		emails.setContent("不舒服");
		emails.setSubject("请假");
		emails.setFromNikeName("小强");
		emails.setHours(7);

		mailService.sendHtmlMail(emails);
	}


	@Test
	public void sendTemplateMailForQuery() throws Exception{
		Emails emails = new Emails();
		emails.setFrom("forevermother@126.com");
		emails.setPassword("Liyangzhou115");
		emails.setTo("182480610@qq.com"+";"+"liyangzhou115@126.com");
		emails.setContent("查询请求");
		emails.setSubject("系统查询");
		emails.setFromNikeName("小强");
		emails.setAskforleave(7);
		emails.setOvertimehour(8);
		emails.setRestHour(1);

		mailService.sendHtmlMailForQuery(emails);
	}
}
