package pers.xyj.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Good;
import pers.xyj.modules.market.domain.entity.User;


/**
 * 商品表(Good)表服务接口
 */
public interface GoodService extends IService<Good> {

    ResponseResult goodList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getGoodDetail(Long id);

    ResponseResult deleteGoodById(Long id);

    ResponseResult getSysGoodDetail(Long id);


    ResponseResult setGoodStatusById(String goodStatusApproved, Long id);

    ResponseResult goodUpload(Good good);

    ResponseResult getUserGoodListByState(Integer pageNum, Integer pageSize, Integer sold);

    ResponseResult getAllGoodListByState(Integer pageNum, Integer pageSize, Long categoryId, String goodState);

    ResponseResult deleteOwnGoodById(Long id);

    ResponseResult getOrderListByState(User user, Integer pageNum, Integer pageSize, Integer orderState);

    ResponseResult goodUpdate(Good good);

    ResponseResult getOrderListByUserId(User user, Integer pageNum, Integer pageSize);

    ResponseResult getDealListByState(User user, Integer pageNum, Integer pageSize, Integer orderState);

    ResponseResult getUnSoldGoodList(Integer pageNum, Integer pageSize, String state);
}
