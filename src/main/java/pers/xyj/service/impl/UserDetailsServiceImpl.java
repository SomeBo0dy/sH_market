package pers.xyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.xyj.domain.entity.LoginUser;
import pers.xyj.domain.entity.User;
import pers.xyj.mapper.MenuMapper;
import pers.xyj.mapper.UserMapper;

import java.util.ArrayList;
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
        queryWrapper.eq(User::getUserName,userName);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否存在该用户，没有则报出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }
        //权限
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        //返回用户信息
        return new LoginUser(user,list);

    }
}
