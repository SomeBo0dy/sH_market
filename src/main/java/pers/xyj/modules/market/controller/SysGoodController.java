package pers.xyj.modules.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.modules.common.annotation.SystemLog;
import pers.xyj.modules.common.constants.SystemConstants;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.UpdateGoodStateDto;
import pers.xyj.modules.market.service.GoodService;
@Api(value = "SysGoodControllerApi",tags={"管理员商品操作接口"})
@RestController
@RequestMapping("/sys/goods")
public class SysGoodController {

    @Autowired
    private GoodService goodService;

    @ApiOperation(value="管理员获取商品具体信息")
    @SystemLog(businessName = "管理员获取商品具体信息")
    @GetMapping("/{id}")
    public ResponseResult getSysGoodDetail(@PathVariable("id") Long id){
        return goodService.getSysGoodDetail(id);
    }

    @ApiOperation(value="删除商品")
    @SystemLog(businessName = "删除商品")
    @DeleteMapping("/{id}")
   // @PreAuthorize("hasAuthority('system:object:delete')")
    public ResponseResult deleteGoodById(@PathVariable("id") Long id){
        return goodService.deleteGoodById(id);
    }

    @ApiOperation(value="管理员获取未审核商品列表")
    @SystemLog(businessName = "管理员获取未审核商品列表")
    @GetMapping("/unGoodList")
    public ResponseResult getUnGoodList(Integer pageNum, Integer pageSize, Long categoryId) {
        return goodService.getAllGoodListByState(pageNum, pageSize, categoryId,SystemConstants.GOOD_STATUS_NOT_APPROVED);
    }

    @ApiOperation(value="管理员修改商品状态")
    @SystemLog(businessName = "管理员修改商品状态")
    @PutMapping
    public ResponseResult setGoodStatusById(@RequestBody UpdateGoodStateDto stateDto) {
        return goodService.setGoodStatusById(stateDto.getState(),stateDto.getGId());
    }
}
