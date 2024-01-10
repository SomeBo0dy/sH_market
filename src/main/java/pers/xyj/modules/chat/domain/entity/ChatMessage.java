package pers.xyj.modules.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("chat_message")
public class ChatMessage {
    private Long messageId;
    private Long linkId;
    private Long fromUser;
    private Long toUser;
    private String content;
    private Date sendTime;
    private Integer type;
    private Boolean isLatest;
}
