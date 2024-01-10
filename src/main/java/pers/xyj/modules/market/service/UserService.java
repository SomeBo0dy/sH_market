package pers.xyj.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.PasswordDto;
import pers.xyj.modules.market.domain.dto.SelectUserPageDto;
import pers.xyj.modules.market.domain.dto.StateDto;
import pers.xyj.modules.market.domain.dto.UserInfoDto;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.entity.User;


/**
 * 用户表(User)表服务接口
 */
public interface UserService extends IService<User> {


    ResponseResult editUserInfo(UserInfoDto userInfoDto);

    ResponseResult editPassword(PasswordDto passwordDto);

    ResponseResult getUserInfo();

    ResponseResult getUserInfoById(Long id);

    ResponseResult getUserInfoByPage(SelectUserPageDto userPageDto);

    ResponseResult setUserState(StateDto stateDto);

    ResponseResult cancelAccount();

    ResponseResult sysCancelAccount(Long userId);

}
