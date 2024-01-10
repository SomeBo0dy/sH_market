package pers.xyj.modules.market.service;

import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.PasswordUserDto;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.domain.entity.User;

public interface UserLoginService {
    ResponseResult logout();


    LoginUser getByPasswordAndType(String account, String password, String type);

    ResponseResult register(PasswordUserDto userRegisterDto);

    LoginUser getByPhoneAndType(String email, String type);
}
