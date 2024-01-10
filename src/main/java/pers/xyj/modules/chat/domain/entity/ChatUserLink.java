package pers.xyj.modules.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("chat_user_link")
public class ChatUserLink {
    private Long linkId;
    private Long fromUser;
    private Long toUser;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;
}
