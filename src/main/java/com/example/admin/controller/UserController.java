package com.example.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.admin.common.Result;
import com.example.admin.dto.UserAddDTO;
import com.example.admin.dto.UserQueryDTO;
import com.example.admin.dto.UserUpdateDTO;
import com.example.admin.entity.User;
import com.example.admin.service.UserService;
import com.example.admin.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/list")
    public Result<List<UserVO>> list(UserQueryDTO dto) {

        return Result.success(
                userService.list(dto)
        );
    }

    @PostMapping ("/page")
    public Result<IPage<UserVO>> page(@RequestBody UserQueryDTO dto ){

        return Result.success(
                userService.page(dto)
        );

    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        System.out.println("get:" + id);
        return userService.getById(id);
    }

    @PostMapping("/add")
    public Result<Void> add(@Validated @RequestBody UserAddDTO dto) {
        userService.add(dto);

        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id) {

        userService.removeById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> update(@Validated @RequestBody UserUpdateDTO dto) {
        userService.update(dto);
        return Result.success();
    }

}