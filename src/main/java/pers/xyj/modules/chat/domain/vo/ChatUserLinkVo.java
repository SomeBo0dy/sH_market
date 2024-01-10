package pers.xyj.modules.chat.domain.vo;

import lombok.Data;

import java.util.Date;
@Data
public class ChatUserLinkVo {
    private Long linkId;
    private Long fromUser;
    private Long toUser;
    private Date createTime;
    private String nickName;
}
