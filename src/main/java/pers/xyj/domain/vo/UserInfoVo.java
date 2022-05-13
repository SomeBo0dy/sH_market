package pers.xyj.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoVo {
    private String nickName;
    private String avatar;
    private String sex;
    private String email;
    private String type;
    //商品数量
    private Long goodCount;
    //被拒绝次数
    private Long rejectCount;
}
