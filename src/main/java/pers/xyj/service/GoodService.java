package pers.xyj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.ResponseResult;
import pers.xyj.domain.entity.Good;


/**
 * 商品表(Good)表服务接口
 *
 * @author makejava
 * @since 2022-04-03 17:37:52
 */
public interface GoodService extends IService<Good> {

    ResponseResult goodList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getGoodDetail(Long id);

    ResponseResult deleteGoodById(Long id);

    ResponseResult getSysGoodDetail(Long id);


    ResponseResult setGoodStatusById(String goodStatusApproved, Long id);

    ResponseResult goodUpload(Good good);

    ResponseResult getUserGoodListByState(Integer pageNum, Integer pageSize, Long categoryId, String goodState, int sold);

    ResponseResult getAllGoodListByState(Integer pageNum, Integer pageSize, Long categoryId, String goodState);

    ResponseResult deleteOwnGoodById(Long id);

    ResponseResult getOrderListByState(Integer pageNum, Integer pageSize, int orderState);

    ResponseResult goodUpdate(Good good);
}
