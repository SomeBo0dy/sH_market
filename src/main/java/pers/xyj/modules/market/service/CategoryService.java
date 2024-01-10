package pers.xyj.modules.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategories();
}   
