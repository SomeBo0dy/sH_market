package pers.xyj.modules.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.service.GoodService;

import java.util.Date;

@Api(value = "GoodControllerApi",tags={"商品操作接口"})
@RestController
@RequestMapping("/goods")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @ApiOperation(value="获取通过审核的上架商品列表")
    @SystemLog(businessName = "获取通过审核的上架商品列表")
    @GetMapping("/goodList")
    public ResponseResult goodList(Integer pageNum, Integer pageSize, Long categoryId){
        return goodService.goodList(pageNum,pageSize,categoryId);
    }

    @ApiOperation(value="获取上架商品具体信息")
    @SystemLog(businessName = "获取上架商品具体信息")
    @GetMapping("/{id}")
    public ResponseResult getGoodDetail(@PathVariable("id") Long id){
        return goodService.getGoodDetail(id);
    }

}
