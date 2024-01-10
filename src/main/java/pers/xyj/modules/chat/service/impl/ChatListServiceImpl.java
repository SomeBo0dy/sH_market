package pers.xyj.modules.chat.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.modules.chat.domain.entity.ChatList;
import pers.xyj.modules.chat.mapper.ChatListMapper;
import pers.xyj.modules.chat.service.ChatListService;
@Service("ChatListService")
public class ChatListServiceImpl extends ServiceImpl<ChatListMapper, ChatList> implements ChatListService {
}
