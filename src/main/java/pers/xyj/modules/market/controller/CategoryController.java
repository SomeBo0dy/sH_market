package pers.xyj.modules.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.service.CategoryService;

import java.util.Date;

@Api(value = "CategoryController",tags={"商品种类操作接口"})
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value="获取商品种类列表")
    @SystemLog(businessName = "获取商品种类列表")
    @GetMapping
    public ResponseResult getCategories(){
        return categoryService.getCategories();
    }

}
