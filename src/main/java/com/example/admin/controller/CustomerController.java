package com.example.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.admin.common.Result;
import com.example.admin.entity.Customer;
import com.example.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers") // 统一前缀
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. 查询所有用户
    @GetMapping("/list")
    public Result<List<Customer>> list(@RequestParam(required = false) Long id) {
        // 1. 判断 id 是否为空 (注意：Long 类型只能用 == null 判断)
        if (id == null) {
            // 没传 id，返回所有数据
            // 查所有：加上排序逻辑
            LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
            wrapper.orderByDesc(Customer::getCreatedAt); // 新增这一行
            return Result.success(customerService.list(wrapper));
        } else {
            // 传了 id，这里需要注意返回值类型匹配
            // 如果 getById 返回的是单个 Customer，需要用 Arrays.asList 包装成 List
            Customer one = customerService.getById(id);

            // 防止查不到数据报空指针，也可以直接返回 Collections.emptyList()
            if (one != null) {
                return Result.success(java.util.Arrays.asList(one));
            }
            return Result.success(java.util.Collections.emptyList());
        }
    }

    // 2. 根据ID查询
    @GetMapping("/detail")
    public Customer getById(@RequestParam Long id) {
        return customerService.getById(id);
    }

    // 3. 新增用户
    @PostMapping("/add")
    public Result<String>save(@RequestBody Customer customer) {
        boolean success = customerService.save(customer);
        if (success) {
            return Result.success("新增成功");
        } else {
            return Result.fail("新增失败");
        }
    }

    // 3. 更新用户
    @PostMapping("/update")
    public Result<String> update(@RequestBody Customer customer) {
        boolean success = customerService.updateById(customer);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    // 4. 删除用户
    @DeleteMapping("/delete")
    public Result<String> remove(@RequestParam Long id) {
        boolean success = customerService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }
}