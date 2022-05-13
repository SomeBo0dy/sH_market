package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;
import pers.xyj.domain.entity.Good;


/**
 * 商品表(Good)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-03 17:37:48
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

    @Select("SELECT COUNT(*) " +
            "FROM sm_good " +
            "WHERE " +
            "user_id = #{userId} AND del_flag = #{flag} ")
    Long getDefCountById(@Param("userId") Long userId,@Param("flag") int goodDef);
}

