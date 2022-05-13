package pers.xyj.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDetailVo {
    private Long gId;
    //商品标题
    private String title;
    //商品名称
    private String name;
    //商品介绍
    private String introduction;
    //所属用户id
    private Long userId;
    //缩略图
    private String thumbnail;
    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //价格
    private Long prize;
    //想要人数
    private Long wantCount;
}
