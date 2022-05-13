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


    @Test
    public void simpleMailTest(){
        System.out.println("hh");
    }
}
