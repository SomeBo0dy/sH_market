package pers.xyj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.xyj.constants.SystemConstants;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.LoginUser;
import pers.xyj.domain.entity.User;
import pers.xyj.domain.vo.UserInfoVo;
import pers.xyj.domain.vo.UserLoginVo;
import pers.xyj.enums.AppHttpCodeEnum;
import pers.xyj.exception.SystemException;
import pers.xyj.service.UserLoginService;
import pers.xyj.utils.BeanCopyUtils;
import pers.xyj.utils.JwtUtil;
import pers.xyj.utils.RedisCache;

import java.util.Objects;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //会把内容传到UserDetailsServiceImpl,返回LoginUser
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取id生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("userlogin:" + userId, loginUser);
        //把token和userInfo封装，返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copeBean(loginUser.getUser(), UserInfoVo.class);
        UserLoginVo vo = new UserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userId
        Long userId = loginUser.getUser().getId();
        //删除redis中的id
        redisCache.deleteObject("userlogin:" + userId);
        return ResponseResult.okResult();
    }
}
