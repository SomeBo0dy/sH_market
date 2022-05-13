package pers.xyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.domain.entity.Order;
import pers.xyj.mapper.OrderMapper;
import pers.xyj.service.OrderService;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2022-05-13 21:58:13
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
