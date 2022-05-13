package pers.xyj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xyj.domain.entity.Category;
import pers.xyj.mapper.CategoryMapper;
import pers.xyj.service.CategoryService;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-04-03 17:59:21
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
