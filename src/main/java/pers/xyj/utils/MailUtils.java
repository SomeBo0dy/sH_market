package pers.xyj.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import pers.xyj.domain.entity.Good;
import pers.xyj.domain.entity.LoginUser;
import pers.xyj.domain.entity.Mail;
import pers.xyj.domain.entity.User;
import pers.xyj.domain.vo.MailVo;


public class MailUtils {

    public static void setMessage(MailVo mailVo, LoginUser loginUser, SimpleMailMessage message){
        String email = loginUser.getUser().getEmail();
        message.setFrom(email);
        message.setTo(mailVo.getMailTo());
        message.setSubject(mailVo.getSubject());
        message.setText(mailVo.getDetailMessage());
    }


    public static String initMessage(Mail mail, Good good, User user, String goodResult, Boolean flag) {
        return "尊敬的 " + user.getNickName() + (user.getSex().equals("0")? "先生":"女士") + "\n" +
                goodResult + "\n" +
                "商品详情：" + "\n" +
                good + "\n" +
                (flag ?"原因: " + mail.getMessage() + "\n":"") +
                "\n" +
                "！！！！！！！感谢您对本平台的支持，欢迎再次使用！！！！！！！";
    }
}
