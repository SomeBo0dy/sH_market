package pers.xyj.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.xyj.modules.auth.mapper.RoleMapper;
import pers.xyj.modules.common.constants.SystemConstants;
import pers.xyj.modules.market.domain.ResponseResult;
import pers.xyj.modules.market.domain.dto.PasswordDto;
import pers.xyj.modules.market.domain.dto.SelectUserPageDto;
import pers.xyj.modules.market.domain.dto.StateDto;
import pers.xyj.modules.market.domain.dto.UserInfoDto;
import pers.xyj.modules.market.domain.entity.Good;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.domain.entity.Order;
import pers.xyj.modules.market.domain.entity.User;
import pers.xyj.modules.market.domain.vo.PageVo;
import pers.xyj.modules.market.domain.vo.SysUserInfoVo;
import pers.xyj.modules.market.domain.vo.UserInfoVo;
import pers.xyj.modules.common.enums.AppHttpCodeEnum;
import pers.xyj.modules.market.mapper.GoodMapper;
import pers.xyj.modules.market.mapper.OrderMapper;
import pers.xyj.modules.market.mapper.UserMapper;
import pers.xyj.modules.common.exception.SystemException;
import pers.xyj.modules.market.service.OrderService;
import pers.xyj.modules.market.service.UserService;
import pers.xyj.modules.market.service.GoodService;
import pers.xyj.modules.common.utils.BeanCopyUtils;
import pers.xyj.modules.common.utils.SecurityUtils;

import java.util.List;
import java.util.Objects;

import static pers.xyj.modules.common.constants.SystemConstants.*;

/**
 * 用户表(User)表服务实现类
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;



    @Override
    public ResponseResult editUserInfo(UserInfoDto userInfoDto) {
        Long id = SecurityUtils.getLoginUser().getUser().getId();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, id);
        updateWrapper.set(User::getAvatar, userInfoDto.getAvatar());
        updateWrapper.set(User::getIntroduction, userInfoDto.getIntroduction());
        updateWrapper.set(User::getNickName, userInfoDto.getNickName());
        updateWrapper.set(User::getSex, userInfoDto.getSex());
        this.update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editPassword(PasswordDto passwordDto) {
        User user = SecurityUtils.getLoginUser().getUser();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, user.getId());
        updateWrapper.set(User::getPassword, passwordEncoder.encode(passwordDto.getPassword()));
        this.update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserInfo() {
        Long id = SecurityUtils.getLoginUser().getUser().getId();
        User user = this.getById(id);
        UserInfoVo userInfoVo = BeanCopyUtils.copeBean(user, UserInfoVo.class);
        userInfoVo.setTypeName(roleMapper.selectById(ROLE_MAP.get(user.getType())).getName());
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult getUserInfoById(Long id) {
        User user = this.getById(id);
        SysUserInfoVo sysUserInfoVo = BeanCopyUtils.copeBean(user, SysUserInfoVo.class);
        sysUserInfoVo.setTypeName(roleMapper.selectById(ROLE_MAP.get(user.getType())).getName());
        return ResponseResult.okResult(sysUserInfoVo);
    }

    @Override
    public ResponseResult getUserInfoByPage(SelectUserPageDto userPageDto) {
        Integer pageNum = userPageDto.getPageNum();
        Integer pageSize = userPageDto.getPageSize();
        String type = userPageDto.getType();
        String state = userPageDto.getState();
        String keyword = userPageDto.getKeyword();
        if (Objects.isNull(pageNum)) {
            throw new SystemException(AppHttpCodeEnum.PAGE_NUM_NOT_NULL);
        }
        if (Objects.isNull(pageSize)) {
            throw new SystemException(AppHttpCodeEnum.PAGE_SIZE_NOT_NULL);
        }
        IPage<UserInfoVo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UserInfoVo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(type)) {
            queryWrapper.eq("u.type", type);
        }
        if (StringUtils.hasText(state)) {
            queryWrapper.eq("u.state", state);
        }
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like("u.nick_name", keyword);
        }
        IPage<UserInfoVo> userPage = userMapper.getUserInfoByPage(page, queryWrapper);
        List<UserInfoVo> records = userPage.getRecords();
        PageVo pageVo = new PageVo(records, userPage.getPages(), userPage.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult setUserState(StateDto stateDto) {
        String toState = stateDto.getToState();
        Long userId = stateDto.getId();
        if (!StringUtils.hasText(toState)) {
            throw new SystemException(AppHttpCodeEnum.STATE_TYPE_NOT_NULL);
        }
        if (Objects.isNull(toState)) {
            throw new SystemException(AppHttpCodeEnum.USER_ID_NOT_NULL);
        }
        if (!toState.equals(STATUS_NORMAL) && !toState.equals(STATUS_BLOCK)) {
            throw new SystemException(AppHttpCodeEnum.STATE_TYPE_ERROR);
        }
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, userId);
        updateWrapper.set(User::getState, toState);
        update(updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult cancelAccount() {
        Long id = SecurityUtils.getLoginUser().getUser().getId();
        boolean result = removeById(id);
        if (!result) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult sysCancelAccount(Long userId) {
        if (Objects.isNull(userId)) {
            throw new SystemException(AppHttpCodeEnum.USER_ID_NOT_NULL);
        }
        boolean result = removeById(userId);
        if (!result) {
            throw new SystemException(AppHttpCodeEnum.ERROR);
        }
        return ResponseResult.okResult();
    }



}
