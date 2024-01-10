package pers.xyj.modules.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.PasswordLoginDto;
import pers.xyj.modules.market.domain.dto.PasswordUserDto;
import pers.xyj.modules.market.domain.entity.User;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.market.service.UserLoginService;

@Api(value = "UserLoginControllerApi", tags = {"登录注册操作接口"})
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userloginService;

    @ApiOperation(value = "用户账号密码登录")
    @SystemLog(businessName = "用户登录")
    @PostMapping("/login/password")
    public ResponseResult loginPassword(@RequestBody PasswordLoginDto userLoginDto) {
        return ResponseResult.okResult();
    }

    @ApiOperation(value = "用户退出登录")
    @SystemLog(businessName = "用户退出登录")
    @PostMapping("/logout")
    public ResponseResult logout() {
        return userloginService.logout();
    }

    @ApiOperation(value = "用户注册")
    @SystemLog(businessName = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody PasswordUserDto userRegisterDto) {
        return userloginService.register(userRegisterDto);
    }

}