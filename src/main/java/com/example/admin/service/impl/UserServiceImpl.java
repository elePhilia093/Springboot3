package com.example.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.entity.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public List<User> getUserListByName(String username) {
        if (username == null || username.trim().isEmpty()){
            return this.list();
        } else {
            return this.list(new QueryWrapper<User>().like("username",username));
        }
    }
}