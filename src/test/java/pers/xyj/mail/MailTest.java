package pers.xyj.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.xyj.domain.entity.Mail;
import pers.xyj.service.MailService;

@SpringBootTest
public class MailTest {
    @Autowired
    private MailService mailService;

    Mail mail = new Mail();

    private void init(){
        mail.setGId(1L);
        mail.setMailTo("979084805@qq.com");
        mail.setSubject("啊对对对");
        mail.setMessage("真好，会发邮件了呢");
    }

    @Test
    public void simpleMailTest(){
        System.out.println("hh");
    }
}
