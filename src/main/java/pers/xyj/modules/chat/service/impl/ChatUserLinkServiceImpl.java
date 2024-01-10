package pers.xyj.modules.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.modules.chat.domain.dto.AddChatUserLinkDto;
import pers.xyj.modules.chat.domain.entity.ChatUserLink;
import pers.xyj.modules.chat.domain.vo.ChatUserLinkVo;
import pers.xyj.modules.chat.mapper.ChatUserLinkMapper;
import pers.xyj.modules.chat.service.ChatUserLinkService;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.SecurityUtils;
import pers.xyj.modules.market.domain.ResponseResult;

import java.util.List;
import java.util.Objects;

@Service("ChatUserLinkService")
public class ChatUserLinkServiceImpl  extends ServiceImpl<ChatUserLinkMapper, ChatUserLink> implements ChatUserLinkService {

    @Autowired
    private ChatUserLinkMapper chatUserLinkMapper;


    @Override
    public ResponseResult getChatUserLinks() {
        Long uId = SecurityUtils.getLoginUser().getUser().getId();
        List<ChatUserLinkVo> links = chatUserLinkMapper.getChatUserLinks(uId);
        return ResponseResult.okResult(links);
    }

    @Override
    public ResponseResult addLink(AddChatUserLinkDto linkDto) {
        Long ownerId = linkDto.getOwnerId();
        Long uId = SecurityUtils.getLoginUser().getUser().getId();
        LambdaQueryWrapper<ChatUserLink> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ChatUserLink::getFromUser,uId);
        queryWrapper1.eq(ChatUserLink::getToUser,ownerId);
        ChatUserLink chatUserLink = chatUserLinkMapper.selectOne(queryWrapper1);
        if (Objects.isNull(chatUserLink)){
            ChatUserLink chatUserLink1 = new ChatUserLink();
            chatUserLink1.setToUser(ownerId);
            chatUserLink1.setFromUser(uId);
            int insert = chatUserLinkMapper.insert(chatUserLink1);
            if (insert != 1){
                throw new SystemException(AppHttpCodeEnum.ERROR);

            }
        }

        LambdaQueryWrapper<ChatUserLink> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ChatUserLink::getFromUser,ownerId);
        queryWrapper2.eq(ChatUserLink::getToUser,uId);
        ChatUserLink chatUserLink2 = chatUserLinkMapper.selectOne(queryWrapper2);
        if (Objects.isNull(chatUserLink2)){
            ChatUserLink chatUserLink3 = new ChatUserLink();
            chatUserLink3.setToUser(uId);
            chatUserLink3.setFromUser(ownerId);
            int insert = chatUserLinkMapper.insert(chatUserLink3);
            if (insert != 1){
                throw new SystemException(AppHttpCodeEnum.ERROR);
            }
        }
        return ResponseResult.okResult();
    }
}
