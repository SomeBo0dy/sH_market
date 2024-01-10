package pers.xyj.modules.market.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.xyj.modules.common.constants.SystemConstants;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.entity.*;
import pers.xyj.modules.market.domain.vo.GoodDetailVo;
import pers.xyj.modules.market.domain.vo.GoodListVo;
import pers.xyj.modules.market.domain.vo.OrderVo;
import pers.xyj.modules.market.domain.vo.PageVo;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.market.mapper.GoodMapper;
import pers.xyj.modules.market.mapper.OrderMapper;
import pers.xyj.modules.market.mapper.UserMapper;
import pers.xyj.modules.market.service.CategoryService;
import pers.xyj.modules.market.service.GoodService;
import pers.xyj.modules.market.service.OrderService;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.List;
import java.util.Objects;

/**
 * 商品表(Good)表服务实现类
 */
@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;


    @Override
    public ResponseResult goodList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getSold, SystemConstants.GOOD_NOT_SOLD);
        queryWrapper.eq(Good::getState, SystemConstants.GOOD_STATUS_APPROVED);
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Good::getCategoryId, categoryId);
        queryWrapper.orderByDesc(Good::getCreateTime);
        IPage<Good> page = new Page<>(pageNum, pageSize);
        IPage<Good> goodIPage = goodMapper.selectPage(page, queryWrapper);
        //封装vo
        List<GoodListVo> goodListVos = BeanCopyUtils.copyBeanList(goodIPage.getRecords(), GoodListVo.class);
        PageVo pageVo = new PageVo(goodListVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getGoodDetail(Long id) {
        Good good = getById(id);
        GoodDetailVo goodDetailVo = BeanCopyUtils.copeBean(good, GoodDetailVo.class);
        Long categoryId = goodDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            goodDetailVo.setCategoryName(category.getName());
        }
        User user = userMapper.selectById(goodDetailVo.getUserId());
        goodDetailVo.setAvatar(user.getAvatar());
        goodDetailVo.setUserName(user.getNickName());
        return ResponseResult.okResult(goodDetailVo);
    }

    @Override
    public ResponseResult deleteGoodById(Long id) {
        goodMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getSysGoodDetail(Long id) {
        Good good = goodMapper.getSysById(id);
        GoodDetailVo goodDetailVo = BeanCopyUtils.copeBean(good, GoodDetailVo.class);
        Long categoryId = goodDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category != null) {
            goodDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(goodDetailVo);
    }

    @Override
    public ResponseResult getAllGoodListByState(Integer pageNum, Integer pageSize, Long categoryId, String goodState) {
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getState, goodState);
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Good::getCategoryId, categoryId);
        Page<Good> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //封装vo
        List<GoodListVo> goodListVos = BeanCopyUtils.copyBeanList(page.getRecords(), GoodListVo.class);
        PageVo pageVo = new PageVo(goodListVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult deleteOwnGoodById(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        Long userId = user.getId();
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getUserId, userId);
        queryWrapper.eq(Good::getGId, id);
        int count = goodMapper.delete(queryWrapper);
        if (count == 0) {
            throw new SystemException(AppHttpCodeEnum.GOOD_NOT_EXIST);
        }
        LambdaQueryWrapper<Order> orderWrapper = new LambdaQueryWrapper<>();
        orderWrapper.eq(Order::getGId, id);
        Order order = new Order();
        order.setState(2);
        orderMapper.update(order, orderWrapper);
        return ResponseResult.okResult();

    }

    @Override
    public ResponseResult getOrderListByState(User user, Integer pageNum, Integer pageSize, Integer orderState) {
        Long userId = user.getId();
        IPage<OrderVo> page = new Page<>(pageNum, pageSize);
        if (orderState < 0){
            orderMapper.getOrderListByUserId(page, userId);
        }else {
            orderMapper.getPage(page, userId, orderState);
        }
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult goodUpdate(Good good) {
        if (!StringUtils.hasText(good.getTitle())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_TITLE_NOT_NULL);
        }
        if (!StringUtils.hasText(good.getName())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_NAME_NOT_NULL);
        }
        if (Objects.isNull(good.getCategoryId())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_CATE_NOT_NULL);
        }
        if (Objects.isNull(good.getPrize())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_PRICE_NOT_NULL);
        }
        Long userId = SecurityUtils.getUserId();
        good.setUserId(userId);
        updateById(good);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getOrderListByUserId(User user, Integer pageNum, Integer pageSize) {
        Long userId = user.getId();
        IPage<OrderVo> page = new Page<>(pageNum, pageSize);
        orderMapper.getOrderListByUserId(page, userId);
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getDealListByState(User user, Integer pageNum, Integer pageSize, Integer orderState) {
        Long userId = user.getId();
        IPage<OrderVo> page = new Page<>(pageNum, pageSize);
        if (orderState < 0){
            orderMapper.getDealListByUserId(page, userId);
        }else {
            orderMapper.getDealPage(page, userId, orderState);
        }
        PageVo pageVo = new PageVo(page.getRecords(), page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getUnSoldGoodList(Integer pageNum, Integer pageSize, String state) {
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        if (Integer.parseInt(state) >=0){
            queryWrapper.eq(Good::getState, state);
        }
        Page<Good> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //封装vo
        List<GoodListVo> goodListVos = BeanCopyUtils.copyBeanList(page.getRecords(), GoodListVo.class);
        PageVo pageVo = new PageVo(goodListVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult setGoodStatusById(String state, Long id) {
        Long result = goodMapper.setGoodStatusById(state, id);
        if (result == 0) {
            throw new SystemException(AppHttpCodeEnum.GOOD_NOT_EXIST);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult goodUpload(Good good) {
        if (!StringUtils.hasText(good.getTitle())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_TITLE_NOT_NULL);
        }
        if (!StringUtils.hasText(good.getName())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_NAME_NOT_NULL);
        }
        if (Objects.isNull(good.getCategoryId())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_CATE_NOT_NULL);
        }
        if (Objects.isNull(good.getPrize())) {
            throw new SystemException(AppHttpCodeEnum.GOOD_PRICE_NOT_NULL);
        }
        Long userId = SecurityUtils.getUserId();
        good.setUserId(userId);
        save(good);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserGoodListByState(Integer pageNum, Integer pageSize, Integer soldState) {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<Good> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Good::getUserId, userId);
        queryWrapper.orderByDesc(Good::getCreateTime);
        if (soldState >=0){
            queryWrapper.eq(Good::getSold, soldState);
        }
        Page<Good> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        //封装vo
        List<GoodListVo> goodListVos = BeanCopyUtils.copyBeanList(page.getRecords(), GoodListVo.class);
        PageVo pageVo = new PageVo(goodListVos, page.getPages(), page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

}
