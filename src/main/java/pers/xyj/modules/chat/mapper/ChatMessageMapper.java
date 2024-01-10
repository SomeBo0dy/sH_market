package pers.xyj.modules.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.chat.domain.entity.ChatMessage;
import pers.xyj.modules.chat.domain.vo.ChatMessageVo;

import java.util.List;

@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

    @Select("SELECT m.*, uf.nick_name as fromUserName, ut.nick_name as toUserName " +
            "FROM chat_message m " +
            "JOIN sys_user as uf ON m.from_user = uf.id " +
            "JOIN sys_user as ut ON m.to_user = ut.id " +
            "WHERE m.from_user = #{fromUser} AND m.to_user = #{toUser} OR m.from_user = #{toUser} AND m.to_user = #{fromUser} " +
            "ORDER BY m.send_time ASC ")
    List<ChatMessageVo> getMessage(@Param("fromUser") Long fromUser,@Param("toUser")  Long toUser);
}
