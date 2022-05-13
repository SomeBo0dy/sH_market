package pers.xyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVo {
    private Long id;

    //商品id
    private Long gId;
    //标题
    private String title;
    //邮件标题
    private String subject;
    //管理员评价
    private String message;
    //邮件内容
    private String detailMessage;
    //邮箱号
    private String mailTo;
    //被退回商品的用户id
    private Long userId;
}
