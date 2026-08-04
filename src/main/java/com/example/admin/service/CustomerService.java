package com.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.admin.entity.Customer;

// 继承 IService，泛型填实体类
public interface CustomerService extends IService<Customer> {
    // 可以在这里定义自定义的业务方法
}