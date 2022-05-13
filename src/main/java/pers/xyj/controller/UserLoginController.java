package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.annotation.SystemLog;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.User;
import pers.xyj.enums.AppHttpCodeEnum;
import pers.xyj.exception.SystemException;
import pers.xyj.service.UserLoginService;
@Api(value = "UserLoginControllerApi",tags={"用户登录操作接口"})
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userloginService;

    @ApiOperation(value="用户登录")
    @SystemLog(businessName = "用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if (!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return userloginService.login(user);
    }

    @ApiOperation(value="用户退出登录")
    @SystemLog(businessName = "用户退出登录")
    @PostMapping("/logout")
    public ResponseResult logout(){
        return userloginService.logout();
    }

}
