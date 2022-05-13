package pers.xyj.service;

import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.User;

public interface UserLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
