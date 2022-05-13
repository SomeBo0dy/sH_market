package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;
import pers.xyj.domain.entity.Menu;

import java.util.List;


/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-03 10:50:39
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Select("SELECT DISTINCT " +
            "m.perms " +
            "FROM " +
            "sys_user_role ur " +
            "LEFT JOIN sys_role r ON ur.role_id = r.id " +
            "LEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id " +
            "LEFT JOIN sys_menu m ON m.id = rm.menu_id " +
            "WHERE " +
            "user_id = #{userId} " +
            "AND r.state = 0 " +
            "AND m.state = 0 ")
    List<String> selectPermsByUserId(@Param("userId") Long userId);
}

