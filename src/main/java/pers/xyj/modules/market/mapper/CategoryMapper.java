package pers.xyj.modules.market.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.xyj.modules.market.domain.entity.Category;


/**
 * 分类表(Category)表数据库访问层
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

