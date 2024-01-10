package pers.xyj.modules.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("chat_list")
public class ChatList {
    private Long listId;
    private Long linkId;
    private Long fromUser;
    private Long toUser;
    private Boolean fromWindow;
    private Boolean toWindow;
    private Integer unread;
    private Boolean status;
}
