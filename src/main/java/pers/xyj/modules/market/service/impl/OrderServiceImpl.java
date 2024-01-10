package pers.xyj.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.common.utils.SecurityUtils;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Good;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.vo.OrderVo;
import pers.xyj.modules.market.mapper.OrderMapper;
import pers.xyj.modules.market.service.GoodService;
import pers.xyj.modules.market.service.OrderService;

import static pers.xyj.modules.common.constants.SystemConstants.GOOD_SOLD;

/**
 * (Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodService goodService;
    @Override
    public ResponseResult orderGood(@RequestBody Long gId) {
        Long uId = SecurityUtils.getLoginUser().getUser().getId();
        Order order = new Order();
        order.setGId(gId);
        order.setUId(uId);
        int insert = orderMapper.insert(order);
        if (insert != 1) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateOrderState(OrderVo order) {
        LambdaUpdateWrapper<Order> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Order::getState,order.getState());
        updateWrapper.eq(Order::getOId,order.getOId());
        boolean update = this.update(updateWrapper);
        if (!update){
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }else {
            if (order.getState()==1){
                LambdaUpdateWrapper<Good> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.set(Good::getSold,GOOD_SOLD);
                lambdaUpdateWrapper.eq(Good::getGId,order.getGId());
                boolean res = goodService.update(lambdaUpdateWrapper);
                if (!res){
                    throw new SystemException(AppHttpCodeEnum.ERROR);
                }
            }

        }
        return ResponseResult.okResult();
    }
}
