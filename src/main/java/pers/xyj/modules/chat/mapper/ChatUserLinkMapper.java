package pers.xyj.modules.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.chat.domain.entity.ChatUserLink;
import pers.xyj.modules.chat.domain.vo.ChatUserLinkVo;

import java.util.List;

@Mapper
public interface ChatUserLinkMapper extends BaseMapper<ChatUserLink> {

    @Select("SELECT ul.*, u.nick_name " +
            "FROM chat_user_link ul " +
            "JOIN sys_user u ON ul.to_user = u.id " +
            "WHERE ul.from_user = #{uId} ")
    List<ChatUserLinkVo> getChatUserLinks(@Param("uId") Long uId);
}
