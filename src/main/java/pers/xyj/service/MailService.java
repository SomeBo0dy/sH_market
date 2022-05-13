package pers.xyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Mail;

public interface MailService extends IService<Mail> {
    ResponseResult sendSimpleMail(Mail mail, String goodRejected);
}
