package com.example.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.entity.User;
import com.example.admin.mapper.UserMapper;
import com.example.admin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

    // @Override   模糊查询写法
    // public List<User> getUserListByName(String username) {
    //     if (username == null || username.trim().isEmpty()){
    //         return this.list();
    //     } else {
    //         return this.list(new QueryWrapper<User>().like("username",username));
    //     }
    // }

    @Override
    public List<User> list(UserQueryDTO dto) {


        LambdaQueryWrapper<User> wrapper =
                new LambdaQueryWrapper<>();


        /**
         * 用户名模糊查询
         */
        wrapper.like(
                StringUtils.hasText(dto.getUsername()),
                User::getUsername,
                dto.getUsername()
        );


        /**
         * 昵称模糊查询
         */
        wrapper.like(
                StringUtils.hasText(dto.getNickname()),
                User::getNickname,
                dto.getNickname()
        );


        /**
         * 邮箱模糊查询
         */
        wrapper.like(
                StringUtils.hasText(dto.getEmail()),
                User::getEmail,
                dto.getEmail()
        );


        /**
         * 手机号模糊查询
         */
        wrapper.like(
                StringUtils.hasText(dto.getPhone()),
                User::getPhone,
                dto.getPhone()
        );



        /**
         * 状态精准查询
         */
        wrapper.eq(
                dto.getStatus()!=null,
                User::getStatus,
                dto.getStatus()
        );



        /**
         * 性别精准查询
         */
        wrapper.eq(
                StringUtils.hasText(dto.getGender()),
                User::getGender,
                dto.getGender()
        );



        /**
         * 部门精准查询
         */
        wrapper.eq(
                dto.getDeptId()!=null,
                User::getDeptId,
                dto.getDeptId()
        );



        /**
         * 年龄范围
         */
        wrapper.ge(
                dto.getMinAge()!=null,
                User::getAge,
                dto.getMinAge()
        );


        wrapper.le(
                dto.getMaxAge()!=null,
                User::getAge,
                dto.getMaxAge()
        );



        /**
         * 创建时间范围
         */
        wrapper.ge(
                dto.getStartTime()!=null,
                User::getCreateTime,
                dto.getStartTime()
        );


        wrapper.le(
                dto.getEndTime()!=null,
                User::getCreateTime,
                dto.getEndTime()
        );



        /**
         * 默认排序
         * 创建时间倒序
         */
        wrapper.orderByDesc(
                User::getCreateTime
        );


        return this.list(wrapper);

    }

    @Override
    public IPage<User> page(
            UserQueryDTO dto,
            Integer current,
            Integer size
    ){


        /**
         * 创建分页对象
         *
         * current 当前页
         * size 每页数量
         */
        Page<User> page =
                new Page<>(
                        current,
                        size
                );



        /**
         * 查询条件
         */
        LambdaQueryWrapper<User> wrapper =
                new LambdaQueryWrapper<>();



        /**
         * 用户名模糊查询
         */
        wrapper.like(
                StringUtils.hasText(dto.getUsername()),

                User::getUsername,

                dto.getUsername()
        );



        /**
         * 状态查询
         */
        wrapper.eq(
                dto.getStatus()!=null,

                User::getStatus,

                dto.getStatus()
        );



        /**
         * 部门查询
         */
        wrapper.eq(
                dto.getDeptId()!=null,

                User::getDeptId,

                dto.getDeptId()
        );



        /**
         * 默认排序
         */
        wrapper.orderByDesc(
                User::getCreateTime
        );



        /**
         * 执行分页查询
         */
        return this.page(
                page,
                wrapper
        );

    }
}