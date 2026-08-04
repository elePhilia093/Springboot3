package com.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.admin.entity.Customer;
import com.example.admin.mapper.CustomerMapper;
import com.example.admin.service.CustomerService;
import org.springframework.stereotype.Service;

@Service // 标记为Spring的服务组件
public class CustomerServiceImpl
        extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {

    // ServiceImpl 已经帮你实现了大部分通用业务逻辑
}