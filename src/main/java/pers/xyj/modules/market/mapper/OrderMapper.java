package pers.xyj.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.vo.OrderVo;

/**
 * (Order)表数据库访问层
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT o.o_id, o.g_id,o.state,o.create_time,o.update_time,g.name,g.title, g.user_id as ownerId, u.nick_name, u.qq_number, g.thumbnail,g.prize " +
            "FROM sm_order o " +
            "JOIN sm_good g ON o.g_id = g.g_id " +
            "JOIN sys_user u ON g.user_id = u.id " +
            "WHERE o.u_id = #{uId} AND o.state = #{state} " +
            "ORDER BY create_time DESC ")
    IPage<OrderVo> getPage(IPage<OrderVo> page, @Param("uId") Long userId, @Param("state") int orderState);

    @Select("SELECT o.o_id, o.g_id,o.state,o.create_time,o.update_time,g.name,g.title, g.user_id as ownerId,u.nick_name, u.qq_number, g.thumbnail,g.prize " +
            "FROM sm_order o " +
            "JOIN sm_good g ON o.g_id = g.g_id " +
            "JOIN sys_user u ON g.user_id = u.id " +
            "WHERE o.u_id = #{uId} " +
            "ORDER BY create_time DESC ")
    IPage<OrderVo> getOrderListByUserId(IPage<OrderVo> page,@Param("uId") Long userId);

    @Select("SELECT o.o_id, o.g_id,o.state,o.create_time,o.update_time,g.name,g.title, g.user_id as ownerId,u.nick_name, u.qq_number, g.thumbnail,g.prize " +
            "FROM sm_order o " +
            "JOIN sm_good g ON o.g_id = g.g_id " +
            "JOIN sys_user u ON g.user_id = u.id " +
            "WHERE g.user_id = #{uId} " +
            "ORDER BY create_time DESC ")
    IPage<OrderVo> getDealListByUserId(IPage<OrderVo> page,@Param("uId") Long userId);

    @Select("SELECT o.o_id, o.g_id,o.state,o.create_time,o.update_time,g.name,g.title, g.user_id as ownerId,u.nick_name, u.qq_number, g.thumbnail,g.prize " +
            "FROM sm_order o " +
            "JOIN sm_good g ON o.g_id = g.g_id " +
            "JOIN sys_user u ON g.user_id = u.id " +
            "WHERE g.user_id = #{uId} AND o.state = #{state} " +
            "ORDER BY create_time DESC ")
    IPage<OrderVo> getDealPage(IPage<OrderVo> page, @Param("uId") Long userId, @Param("state") Integer orderState);
}

