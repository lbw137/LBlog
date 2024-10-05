package com.lyb.lyblog.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyb.lyblog.mapper.UserMapper;
import com.lyb.lyblog.model.PO.User;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    // 验证用户信息
    public User checkUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername, username);
        queryWrapper.eq(User::getPassword, password);
        return getOne(queryWrapper);
    }
}