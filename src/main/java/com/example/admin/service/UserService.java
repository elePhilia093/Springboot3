package com.example.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.entity.User;
import com.example.admin.vo.UserVO;

import java.util.List;


public interface UserService extends IService<User> {

    List<UserVO> list(UserQueryDTO dto);


    IPage<UserVO> page(UserQueryDTO dto);
}