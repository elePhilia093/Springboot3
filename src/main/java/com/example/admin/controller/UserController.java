package com.example.admin.controller;


import com.example.admin.entity.User;
import com.example.admin.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
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
    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id){
        System.out.println("get:" + id);
        return userService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.removeById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userService.updateById(user);
    }

}