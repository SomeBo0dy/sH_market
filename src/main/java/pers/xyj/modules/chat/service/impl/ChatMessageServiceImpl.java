package pers.xyj.modules.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.modules.chat.domain.entity.ChatMessage;
import pers.xyj.modules.chat.domain.vo.ChatMessageVo;
import pers.xyj.modules.chat.mapper.ChatMessageMapper;
import pers.xyj.modules.chat.service.ChatMessageService;
import pers.xyj.modules.common.utils.SecurityUtils;
import pers.xyj.modules.market.domain.ResponseResult;

import java.util.List;

@Service("ChatMessageService")
public class ChatMessageServiceImpl  extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService{

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Override
    public ResponseResult getMessage(Long toUser) {
        Long fromUser = SecurityUtils.getLoginUser().getUser().getId();
        List<ChatMessageVo> messageVos =  chatMessageMapper.getMessage(fromUser, toUser);
        return ResponseResult.okResult(messageVos);
    }
}
