package pers.xyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-03-26 14:59:59
 */
public interface UserService extends IService<User> {

    ResponseResult register(User user);

    ResponseResult userInfo(Long userId);

    ResponseResult updateUserInfo(User user);
}
