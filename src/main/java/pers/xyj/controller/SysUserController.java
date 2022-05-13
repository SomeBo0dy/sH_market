package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.annotation.SystemLog;
import pers.xyj.domain.ResponseResult;
import pers.xyj.service.UserService;
import pers.xyj.utils.SecurityUtils;
@Api(value = "SysUserControllerApi",tags={"管理员用户操作接口"})
@RestController
@RequestMapping("/sys/users")
public class SysUserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="管理员查询用户信息")
    @SystemLog(businessName = "管理员查询用户信息")
    @GetMapping("/userInfo/{id}")
    public ResponseResult userInfo(@PathVariable("id") Long userId){
        return userService.userInfo(userId);
    }
}
