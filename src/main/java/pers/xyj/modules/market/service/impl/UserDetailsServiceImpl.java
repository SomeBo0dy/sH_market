package pers.xyj.modules.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.xyj.modules.auth.mapper.MenuMapper;
import pers.xyj.modules.common.constants.SystemConstants;
import pers.xyj.modules.market.domain.entity.LoginUser;
import pers.xyj.modules.market.domain.entity.User;
import pers.xyj.modules.market.mapper.UserMapper;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据用户名查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, userName);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否存在该用户，没有则报出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        if (user.getState().equals(SystemConstants.STATUS_BLOCK)) {
            throw new RuntimeException("用户已被封号");
        }
        //权限
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //返回用户信息
        return new LoginUser(user, list);
    }

}
