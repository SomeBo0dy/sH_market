package pers.xyj.modules.market.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfoVo userInfoVo;
//    private List<MenuVo> authVo;
}
