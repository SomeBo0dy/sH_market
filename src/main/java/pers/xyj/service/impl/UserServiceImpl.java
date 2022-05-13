package pers.xyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.xyj.constants.SystemConstants;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Good;
import pers.xyj.domain.entity.User;
import pers.xyj.domain.vo.UserInfoVo;
import pers.xyj.enums.AppHttpCodeEnum;
import pers.xyj.mapper.GoodMapper;
import pers.xyj.mapper.UserMapper;
import pers.xyj.exception.SystemException;
import pers.xyj.service.GoodService;
import pers.xyj.service.UserService;
import pers.xyj.utils.BeanCopyUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-26 14:59:59
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodMapper goodMapper;

    @Override
    public ResponseResult register(User user) {
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (userNameExist(user)){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (emailExist(user)){
            throw  new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //对密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userInfo(Long userId) {
        User user = getById(userId);
        //更新用户信息
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getUserId,userId);
        queryWrapper.eq(Good::getState, SystemConstants.GOOD_STATUS_APPROVED);
        Long goodCount = Long.valueOf(goodService.count(queryWrapper));
        user.setGoodCount(goodCount);
        Long rejectCount = goodMapper.getDefCountById(userId,SystemConstants.GOOD_DEF);
        user.setRejectCount(rejectCount);
        updateById(user);
        //封装vo
        UserInfoVo vo = BeanCopyUtils.copeBean(user,UserInfoVo.class);

        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    private boolean emailExist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,user.getEmail());
        return count(queryWrapper) > 0;
    }

    private boolean userNameExist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        return count(queryWrapper) > 0;
    }
}
