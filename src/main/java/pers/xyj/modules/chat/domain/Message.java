package pers.xyj.modules.chat.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;

    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;


    //信息id（自增）
    private Long messageId;

    //关系表id
    private Long linkId;

    //发送者
    private Long fromUser;

    //接收者
    private Long toUser;

    //内容
    private String content;

    //发送时间
        private Date sendTime;

    //消息类型  0--普通文本（默认）
    private int type = MESSAGE_TYPE_TEXT;

    //是否为最后一条
    private Boolean isLatest;
}

