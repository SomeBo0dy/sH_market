package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.domain.entity.Order;
import pers.xyj.domain.vo.OrderVo;

/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-13 21:58:09
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT o.g_id,o.state,o.create_time,o.update_time,g.name,u.user_name,g.thumbnail,g.prize " +
            "FROM sm_order o " +
            "JOIN sm_good g ON o.g_id = g.g_id " +
            "JOIN sys_user u ON g.user_id = u.id " +
            "WHERE o.u_id = #{uId} AND o.state = #{state} AND o.del_flag = 0")
    IPage<OrderVo> getPage(IPage<OrderVo> page, @Param("uId") Long userId,@Param("state") int orderState);
}

