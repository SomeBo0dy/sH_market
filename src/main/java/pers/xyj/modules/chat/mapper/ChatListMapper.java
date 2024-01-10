package pers.xyj.modules.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.modules.chat.domain.entity.ChatList;
@Mapper
public interface ChatListMapper extends BaseMapper<ChatList> {
}
