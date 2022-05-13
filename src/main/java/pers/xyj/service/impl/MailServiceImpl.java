package pers.xyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Good;
import pers.xyj.domain.entity.LoginUser;
import pers.xyj.domain.entity.Mail;
import pers.xyj.domain.entity.User;
import pers.xyj.domain.vo.MailVo;
import pers.xyj.mapper.MailMapper;
import pers.xyj.service.GoodService;
import pers.xyj.service.MailService;
import pers.xyj.service.UserService;
import pers.xyj.utils.BeanCopyUtils;
import pers.xyj.utils.MailUtils;
import pers.xyj.utils.SecurityUtils;

@Service
@Slf4j
//@ConfigurationProperties(prefix = "")
public class MailServiceImpl extends ServiceImpl<MailMapper,Mail> implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;

    @Override
    public ResponseResult sendSimpleMail(Mail mail, String goodResult) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long gId = mail.getGId();
        Good good = goodService.getById(gId);
        Long userId = good.getUserId();
        User user = userService.getById(userId);
        mail.setUserId(userId);
        mail.setMailTo(user.getEmail());
        mail.setSubject(goodResult);
        save(mail);
        MailVo mailVo = BeanCopyUtils.copeBean(mail, MailVo.class);
        Boolean flag = StringUtils.hasText(mail.getMessage());
        mailVo.setDetailMessage(MailUtils.initMessage(mail,good,user,goodResult,flag));
        SimpleMailMessage message = new SimpleMailMessage();
        MailUtils.setMessage(mailVo,loginUser,message);
        mailSender.send(message);
        log.info("邮件已发送");
        return ResponseResult.okResult();
    }


}
