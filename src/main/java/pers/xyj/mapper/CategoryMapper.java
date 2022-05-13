package pers.xyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.domain.entity.Category;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-03 17:59:18
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

