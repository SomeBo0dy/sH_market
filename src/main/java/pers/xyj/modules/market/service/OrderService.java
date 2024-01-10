package pers.xyj.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.vo.OrderVo;


/**
 * (Order)表服务接口
 */
public interface OrderService extends IService<Order> {

    ResponseResult orderGood(Long id);

    ResponseResult updateOrderState(OrderVo order);
}   
