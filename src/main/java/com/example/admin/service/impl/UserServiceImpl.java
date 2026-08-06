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
import com.example.admin.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class UserServiceImpl
        extends ServiceImpl<UserMapper, User>
        implements UserService {

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
                dto.getUsername()!=null,
                User::getUsername,
                dto.getUsername()
        );


        IPage<User> userPage =
                this.page(page,wrapper);



        return userPage.convert(this::convert);

    }

    private UserVO convert(User user) {
        // UserVO vo = new UserVO();
        //
        // vo.setId(user.getId());
        //
        // vo.setUsername(
        //         user.getUsername()
        // );
        //
        // vo.setNickname(
        //         user.getNickname()
        // );
        //
        // vo.setEmail(
        //         user.getEmail()
        // );
        //
        // vo.setPhone(
        //         user.getPhone()
        // );
        //
        // vo.setStatus(
        //         user.getStatus()
        // );
        //
        // vo.setDeptId(
        //         user.getDeptId()
        // );
        //
        // vo.setCreateTime(
        //         user.getCreateTime()
        // );
        //
        // vo.setUpdateTime(
        //         user.getUpdateTime()
        // );
        //
        //
        // return vo;

        UserVO vo=new UserVO();

        BeanUtils.copyProperties(user,vo);

        return vo;
    }
}