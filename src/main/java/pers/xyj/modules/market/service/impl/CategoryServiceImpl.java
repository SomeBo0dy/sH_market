package pers.xyj.modules.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Category;
import pers.xyj.modules.market.mapper.CategoryMapper;
import pers.xyj.modules.market.service.CategoryService;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult getCategories() {
        List<Category> categories = categoryMapper.selectList(null);
        return ResponseResult.okResult(categories);
    }
}
