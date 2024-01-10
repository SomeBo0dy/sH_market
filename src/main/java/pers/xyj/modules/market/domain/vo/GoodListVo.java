package pers.xyj.modules.market.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodListVo {
    private Long gId;
    //商品标题
    private String title;
    private String name;
    //所属用户id
    private Long userId;
    private String state;
    //缩略图
    private String thumbnail;
    //所属分类id
    private Long categoryId;
    //价格
    private Double prize;
    //想要人数
    private Long wantCount;
    private Date createTime;
}
