package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.xyj.annotation.SystemLog;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.User;
import pers.xyj.service.UserService;
import pers.xyj.utils.SecurityUtils;
@Api(value = "UserControllerApi",tags={"用户操作接口"})
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户注册")
    @SystemLog(businessName = "用户注册")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }

    @ApiOperation(value="修改用户信息")
    @SystemLog(businessName = "修改用户信息")
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @ApiOperation(value="用户查询用户信息")
    @SystemLog(businessName = "用户查询用户信息")
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        Long userId = SecurityUtils.getUserId();
        return userService.userInfo(userId);
    }



}
