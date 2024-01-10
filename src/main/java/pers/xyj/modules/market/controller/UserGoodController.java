package pers.xyj.modules.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.common.constants.SystemConstants;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.OrderDto;
import pers.xyj.modules.market.domain.entity.Good;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.entity.User;
import pers.xyj.modules.market.domain.vo.OrderVo;
import pers.xyj.modules.market.service.GoodService;
import pers.xyj.modules.market.service.OrderService;
import pers.xyj.modules.market.service.UserService;
import pers.xyj.modules.common.utils.SecurityUtils;

@Api(value = "UserControllerApi",tags={"用户商品操作接口"})
@RestController
@RequestMapping("/user")
public class UserGoodController {
    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @ApiOperation(value="提交上传商品信息")
    @SystemLog(businessName = "提交上传商品信息")
    @PostMapping("/goods/upload")
    public ResponseResult goodUpload(@RequestBody Good good){
        return goodService.goodUpload(good);
    }

    @ApiOperation(value="修改商品信息")
    @SystemLog(businessName = "修改商品信息")
    @PutMapping("/goods/update")
    public ResponseResult goodUpdate(@RequestBody Good good){
        return goodService.goodUpdate(good);
    }

    @ApiOperation(value="获取该用户的商品列表")
    @SystemLog(businessName = "获取该用户的商品列表")
    @GetMapping("/goodsList")
    public ResponseResult getGoodsList(Integer pageNum, Integer pageSize, Integer soldState){
        return goodService.getUserGoodListByState(pageNum,pageSize,soldState-1);
    }
    @ApiOperation(value="获取该用户通过审核的未出售商品列表")
    @SystemLog(businessName = "获取该用户通过审核的未出售商品列表")
    @GetMapping("/goodList/unsold")
    public ResponseResult getUnSoldGoodList(Integer pageNum, Integer pageSize, String state){
        return goodService.getUnSoldGoodList(pageNum,pageSize, state);
    }

//    @ApiOperation(value="获取该用户通过审核的已出售商品列表")
//    @SystemLog(businessName = "获取该用户通过审核的已出售商品列表")
//    @GetMapping("/goodList/sold")
//    public ResponseResult soldGoodList(Integer pageNum, Integer pageSize, Long categoryId){
//        return goodService.getUserGoodListByState(pageNum,pageSize, SystemConstants.GOOD_SOLD);
//    }

    @ApiOperation(value="获取该用户未通过审核的商品列表")
    @SystemLog(businessName = "获取该用户未通过审核的商品列表")
    @GetMapping("/goodList/unapproved")
    public ResponseResult unapprovedGoodList(Integer pageNum, Integer pageSize, Long categoryId){
        return goodService.getUserGoodListByState(pageNum,pageSize, SystemConstants.GOOD_NOT_SOLD);
    }

    @ApiOperation(value="下架该用户的商品")
    @SystemLog(businessName = "下架该用户的商品")
    @DeleteMapping("/goods/{id}")
    public ResponseResult deleteOwnGoodById(@PathVariable("id") Long id){
        return goodService.deleteOwnGoodById(id);
    }

    @ApiOperation(value="获取该用户的订单列表")
    @SystemLog(businessName = "获取该用户的订单列表")
    @GetMapping("/orderList")
    public ResponseResult getOrderList(Integer pageNum, Integer pageSize, Integer orderState){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getOrderListByState(user,pageNum,pageSize, orderState - 1);
    }
    @ApiOperation(value="获取该用户的交易列表")
    @SystemLog(businessName = "获取该用户的交易列表")
    @GetMapping("/dealList")
    public ResponseResult getDealList(Integer pageNum, Integer pageSize, Integer orderState){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getDealListByState(user,pageNum,pageSize, orderState - 1);
    }
    @ApiOperation(value="获取该用户的交易完成的订单列表")
    @SystemLog(businessName = "获取该用户的交易完成的订单列表")
    @GetMapping("/goodList/doOrder")
    public ResponseResult doOrderList(Integer pageNum, Integer pageSize){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getOrderListByState(user,pageNum,pageSize, SystemConstants.ORDER_DONE);
    }

    @ApiOperation(value="获取该用户的交易处理中的订单列表")
    @SystemLog(businessName = "获取该用户的交易处理中的订单列表")
    @GetMapping("/goodList/unOrder")
    public ResponseResult unOrderList(Integer pageNum, Integer pageSize){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        return goodService.getOrderListByState(user,pageNum,pageSize, SystemConstants.ORDER_UNDO);
    }
    @ApiOperation(value = "用户下订单")
    @SystemLog(businessName = "用户下订单")
    @PostMapping("/order")
    public ResponseResult orderGood(@RequestBody OrderDto order){
//        return ResponseResult.okResult();
        return orderService.orderGood(order.getGId());
    }
    @ApiOperation(value = "修改订单状态")
    @SystemLog(businessName = "修改订单状态")
    @PutMapping("/order")
    public ResponseResult updateOrderState(@RequestBody OrderVo order){
        return orderService.updateOrderState(order);

//        return userService.deal(order);
    }
    @ApiOperation(value = "卖家确定成交")
    @SystemLog(businessName = "卖家确定成交")
    @PostMapping("/deal")
    public ResponseResult deal(@RequestBody Order order){
        return ResponseResult.okResult();

//        return userService.deal(order);
    }
}
