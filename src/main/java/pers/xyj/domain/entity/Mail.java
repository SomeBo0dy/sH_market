package pers.xyj.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮箱(Mail)表实体类
 *
 * @author makejava
 * @since 2022-04-06 20:04:15
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sm_mail")
public class Mail  {
    @TableId(type = IdType.AUTO)
    private Long id;

    //商品id
    private Long gId;
    //标题
    private String title;
    //邮件标题
    private String subject;
    //邮件内容
    private String message;
    //邮箱号
    private String mailTo;
    //被退回商品的用户id
    private Long userId;

    //创建人的用户id
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer delFlag;



}

