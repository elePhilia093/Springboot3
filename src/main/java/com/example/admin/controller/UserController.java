package com.example.admin.controller;


import com.example.admin.entity.User;
import com.example.admin.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController  //
@RequestMapping("/user")
public class UserController {


    private final IUserService userService;


    public UserController(IUserService userService){
        this.userService = userService;
    }


    @GetMapping("/list")
    public List<User> list(){

        return userService.list();

    }

}