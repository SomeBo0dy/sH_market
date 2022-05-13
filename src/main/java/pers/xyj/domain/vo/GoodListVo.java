package pers.xyj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodListVo {
    private Long gId;
    //商品标题
    private String title;
    //所属用户id
    private Long userId;
    //缩略图
    private String thumbnail;
    //所属分类id
    private Long categoryId;
    //价格
    private Long prize;
    //想要人数
    private Long wantCount;
}
