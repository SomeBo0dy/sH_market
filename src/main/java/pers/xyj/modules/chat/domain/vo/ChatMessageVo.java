package pers.xyj.modules.chat.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessageVo {
    private Long messageId;
    private Long linkId;
    private Long fromUser;
    private Long toUser;
    private String content;
    private Date sendTime;
    private Integer type;
    private Boolean isLatest;
    private String fromUserName;
    private String toUserName;

}
