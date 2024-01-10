package pers.xyj.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.xyj.modules.market.domain.entity.Good;


/**
 * 商品表(Good)表数据库访问层
 */
@Mapper
public interface GoodMapper extends BaseMapper<Good> {

    @Select("SELECT " +
            " * " +
            "FROM " +
            "sm_good " +
            "WHERE " +
            "g_id = #{gId} " +
            "AND state = 0 ")
    Good getSysById(@Param("gId") Long id);


    @Update("UPDATE " +
            "sm_good " +
            "SET " +
            "state = #{state} " +
            "WHERE " +
            "g_id = #{gId} ")
    Long setGoodStatusById(@Param("state") String status,@Param("gId") Long id);
}

