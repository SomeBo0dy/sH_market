package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import pers.xyj.domain.entity.User;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-26 14:59:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Update("UPDATE " +
//            "sys_user " +
//            "SET " +
//            "good_count = good_count + 1 " +
//            "WHERE " +
//            "id = #{uId} ")
//    Long updateGoodCountById(@Param("uId") Long id);
}

