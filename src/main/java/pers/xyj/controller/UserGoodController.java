package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pers.xyj.annotation.SystemLog;
import pers.xyj.constants.SystemConstants;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Good;
import pers.xyj.domain.entity.LoginUser;
import pers.xyj.domain.entity.Order;
import pers.xyj.domain.entity.User;
import pers.xyj.service.GoodService;
import pers.xyj.service.UserService;
import pers.xyj.utils.SecurityUtils;

@Api(value = "UserControllerApi",tags={"用户商品操作接口"})
@RestController
@RequestMapping("/user")
public class UserGoodController {
    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="提交上传商品信息")
    @SystemLog(businessName = "提交上传商品信息")
    @PostMapping("/goods/upload")
    public ResponseResult goodUpload(@RequestBody Good good){
        return goodService.goodUpload(good);
    }

    @ApiOperation(value="修改商品信息")
    @SystemLog(businessName = "修改商品信息")
    @PutMapping("/goods/upload")
    public ResponseResult goodUpdate(@RequestBody Good good){
        return goodService.goodUpdate(good);
    }

    @ApiOperation(value="获取该用户通过审核的未出售商品列表")
    @SystemLog(businessName = "获取该用户通过审核的未出售商品列表")
    @GetMapping("/goodList/unsold")
    public ResponseResult unSoldGoodList(Integer pageNum, Integer pageSize, Long categoryId){
        return goodService.getUserGoodListByState(pageNum,pageSize,categoryId, SystemConstants.GOOD_STATUS_APPROVED,SystemConstants.GOOD_NOT_SOLD);
    }

    @ApiOperation(value="获取该用户通过审核的已出售商品列表")
    @SystemLog(businessName = "获取该用户通过审核的已出售商品列表")
    @GetMapping("/goodList/sold")
    public ResponseResult soldGoodList(Integer pageNum, Integer pageSize, Long categoryId){
        return goodService.getUserGoodListByState(pageNum,pageSize,categoryId, SystemConstants.GOOD_STATUS_APPROVED,SystemConstants.GOOD_SOLD);
    }

    @ApiOperation(value="获取该用户未通过审核的商品列表")
    @SystemLog(businessName = "获取该用户未通过审核的商品列表")
    @GetMapping("/goodList/unapproved")
    public ResponseResult unapprovedGoodList(Integer pageNum, Integer pageSize, Long categoryId){
        return goodService.getUserGoodListByState(pageNum,pageSize,categoryId, SystemConstants.GOOD_STATUS_NOT_APPROVED,SystemConstants.GOOD_NOT_SOLD);
    }

    @ApiOperation(value="下架该用户的商品")
    @SystemLog(businessName = "下架该用户的商品")
    @DeleteMapping("/goods/{id}")
    public ResponseResult deleteOwnGoodById(@PathVariable("id") Long id){
        return goodService.deleteOwnGoodById(id);
    }

    @ApiOperation(value="获取该用户的交易完成的商品列表")
    @SystemLog(businessName = "获取该用户的交易完成的商品列表")
    @GetMapping("/goodList/doOrder")
    public ResponseResult doOrderList(Integer pageNum, Integer pageSize){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getOrderListByState(user,pageNum,pageSize, SystemConstants.ORDER_DONE);
    }

    @ApiOperation(value="获取该用户的交易处理中的商品列表")
    @SystemLog(businessName = "获取该用户的交易处理中的商品列表")
    @GetMapping("/goodList/unOrder")
    public ResponseResult unOrderList(Integer pageNum, Integer pageSize){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getOrderListByState(user,pageNum,pageSize, SystemConstants.ORDER_UNDO);
    }
    @ApiOperation(value = "用户下订单")
    @SystemLog(businessName = "用户下订单")
    @PostMapping("/order/{id}")
    public ResponseResult orderGood(@PathVariable Long id){
        return userService.orderGood(id);
    }
    @ApiOperation(value = "卖家确定成交")
    @SystemLog(businessName = "卖家确定成交")
    @PostMapping("/deal")
    public ResponseResult deal(@RequestBody Order order){
        return userService.deal(order);
    }
}
