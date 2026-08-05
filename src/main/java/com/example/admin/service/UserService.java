package com.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.entity.User;

import java.util.List;


public interface UserService extends IService<User> {

    List<User> list(UserQueryDTO dto);
}