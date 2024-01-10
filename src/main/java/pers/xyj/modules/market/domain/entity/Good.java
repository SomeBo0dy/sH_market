package pers.xyj.modules.market.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 商品表(Good)表实体类
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sm_good")
public class Good  {

    @TableId(type = IdType.AUTO)
    private Long gId;

    //商品标题
    private String title;
    //商品名称
    private String name;
    //商品介绍
    private String introduction;
    //所属分类id
    private Long categoryId;
    //分类名
    @TableField(exist = false)
    private String categoryName;
    //所属用户id
    private Long userId;
    //缩略图
    private String thumbnail;
    //价格
    private Double prize;

    //是否出售(0未出售，1已出售)
    private String sold;

    //状态（0已审核，1未审核）
    private String state;

    //想要人数
    private Long wantCount;
    //是否允许评论 1是，0否
    private String isComment;

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
    //是否置顶（0否，1是）
    private String isTop;

    @Override
    public String toString() {
        return  " 标题: " + title + '\n' +
                " 商品名: " + name + '\n' +
                " 价格: " + prize + '\n' +
                " 商品介绍: " + introduction + '\n' +
                " 商品上传时间: " + createTime + '\n';
    }
}

