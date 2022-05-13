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
import pers.xyj.service.GoodService;
@Api(value = "UserControllerApi",tags={"用户商品操作接口"})
@RestController
@RequestMapping("/user")
public class UserGoodController {
    @Autowired
    private GoodService goodService;

    @ApiOperation(value="提交上传商品信息")
    @SystemLog(businessName = "提交上传商品信息")
    @PreAuthorize("hasAuthority('system:object:upload')")
    @PostMapping("/goods/upload")
    public ResponseResult goodUpload(@RequestBody Good good){
        return goodService.goodUpload(good);
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
        return goodService.getOrderListByState(pageNum,pageSize, SystemConstants.ORDER_DONE);
    }

    @ApiOperation(value="获取该用户的交易处理中的商品列表")
    @SystemLog(businessName = "获取该用户的交易处理中的商品列表")
    @GetMapping("/goodList/unOrder")
    public ResponseResult unOrderList(Integer pageNum, Integer pageSize){
        return goodService.getOrderListByState(pageNum,pageSize, SystemConstants.ORDER_UNDO);
    }
}
