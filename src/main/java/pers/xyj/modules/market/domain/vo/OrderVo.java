package pers.xyj.modules.market.domain.vo;

import lombok.Data;

import java.util.Date;
@Data
public class OrderVo {
    private Long oId;
    private Long gId;
    //0交易处理中，1交易成功
    private Integer state;

    private Date createTime;

    private Date updateTime;
    //商品名称
    private String name;

    private String title;
    //所有者名字
    private Long ownerId;
    private String nickName;
    private String qqNumber;
    //缩略图
    private String thumbnail;
    //价格
    private Double prize;
}
