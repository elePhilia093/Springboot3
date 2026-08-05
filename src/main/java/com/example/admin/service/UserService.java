package com.example.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.entity.User;

import java.util.List;


public interface UserService extends IService<User> {

    List<User> list(UserQueryDTO dto);

    /**
     * 分页查询用户
     */
    IPage<User> page(
            UserQueryDTO dto,
            Integer current,
            Integer size
    );
}