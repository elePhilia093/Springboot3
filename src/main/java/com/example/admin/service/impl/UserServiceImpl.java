package com.example.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.common.ResultCode;
import com.example.admin.dto.UserAddDTO;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.dto.UserUpdateDTO;
import com.example.admin.entity.User;
import com.example.admin.exception.BusinessException;
import com.example.admin.mapper.UserMapper;
import com.example.admin.service.UserService;
import com.example.admin.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public void update(UserUpdateDTO dto) {
        User user = getById(dto.getId());


        if (user == null) {

            throw new BusinessException(
                    ResultCode.USER_NOT_EXIST
            );

        }


        // 2. DTO转换Entity


        BeanUtils.copyProperties(
                dto,
                user
        );


        // 3. 修改时间

        user.setUpdateTime(
                LocalDateTime.now()
        );


        // 4. 更新

        updateById(user);

    }

    @Override
    public void add(UserAddDTO dto) {


        // 1. 判断用户名是否存在

        LambdaQueryWrapper<User> wrapper =
                new LambdaQueryWrapper<>();


        wrapper.eq(
                User::getUsername,
                dto.getUsername()
        );


        Long count = count(wrapper);


        if (count > 0) {

            throw new BusinessException(
                    ResultCode.USER_EXIST
            );

        }


        // 2. DTO转换Entity

        User user = new User();


        BeanUtils.copyProperties(
                dto,
                user
        );


        // 3. 设置默认字段

        user.setPassword(
                "123456"
        );


        user.setCreateTime(
                LocalDateTime.now()
        );


        user.setUpdateTime(
                LocalDateTime.now()
        );


        // 4. 保存数据库

        save(user);


    }

    @Override
    public List<UserVO> list(UserQueryDTO dto) {


        LambdaQueryWrapper<User> wrapper =
                new LambdaQueryWrapper<>();


        wrapper.like(
                dto.getUsername() != null,
                User::getUsername,
                dto.getUsername()
        );


        List<User> users =
                this.list(wrapper);


        return users.stream()
                .map(this::convert)
                .toList();

    }

    @Override
    public IPage<UserVO> page(UserQueryDTO dto) {
        int pageNum = (dto.getPageNum() != null && dto.getPageNum() > 0) ? dto.getPageNum() : 1;
        int pageSize = (dto.getPageSize() != null && dto.getPageSize() > 0) ? dto.getPageSize() : 10;

        /**
         * 创建分页对象
         *
         * current 当前页
         * size 每页数量
         */
        Page<User> page = new Page<>(pageNum, pageSize);


        /**
         * 查询条件
         */
        LambdaQueryWrapper<User> wrapper =
                new LambdaQueryWrapper<>();


        wrapper.like(
                dto.getUsername() != null,
                User::getUsername,
                dto.getUsername()
        );

        wrapper.like(
                dto.getPhone() != null,
                User::getPhone,
                dto.getPhone()
        );

        wrapper.eq(
                dto.getStatus() != null,
                User::getStatus,
                dto.getStatus()
        );

        wrapper.orderByDesc(
                User::getCreateTime
        );


        IPage<User> userPage =
                this.page(page, wrapper);


        return userPage.convert(this::convert);

    }

    private UserVO convert(User user) {

        UserVO vo = new UserVO();

        BeanUtils.copyProperties(user, vo);
        if (user.getCreateTime() != null) {

            vo.setCreateTime(
                    user.getCreateTime()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd HH:mm:ss"
                                    )
                            )
            );

        }


        if (user.getUpdateTime() != null) {

            vo.setUpdateTime(
                    user.getUpdateTime()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyy-MM-dd HH:mm:ss"
                                    )
                            )
            );

        }


        return vo;
    }
}