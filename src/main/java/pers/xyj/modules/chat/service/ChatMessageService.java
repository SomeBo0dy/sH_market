package pers.xyj.modules.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.chat.domain.entity.ChatMessage;
import pers.xyj.modules.market.domain.ResponseResult;

public interface ChatMessageService  extends IService<ChatMessage> {
    ResponseResult getMessage(Long toUser);
}
