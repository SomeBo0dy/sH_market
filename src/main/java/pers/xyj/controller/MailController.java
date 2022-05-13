package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.annotation.SystemLog;
import pers.xyj.constants.SystemConstants;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Mail;
import pers.xyj.service.MailService;
@Api(value = "MailControllerApi",tags={"邮件操作接口"})
@RestController
@RequestMapping("/sys/mail")
public class MailController {
    @Autowired
    MailService mailService;

    @ApiOperation(value="发送拒绝邮件")
    @SystemLog(businessName = "发送拒绝邮件")
    @PostMapping("/reject")
    public ResponseResult postRejectMail(@RequestBody Mail mail){
        return mailService.sendSimpleMail(mail, SystemConstants.GOOD_REJECTED_MESSAGE);
    }

    @ApiOperation(value="发送通过邮件")
    @SystemLog(businessName = "发送通过邮件")
    @PostMapping("/pass")
    public ResponseResult postPassMail(@RequestBody Mail mail){
        return mailService.sendSimpleMail(mail, SystemConstants.GOOD_APPROVED_MESSAGE);
    }
}
