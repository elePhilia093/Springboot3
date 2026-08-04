package com.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper接口
 * 继承 BaseMapper<Customer> 后，无需编写任何方法即可拥有基础的CRUD功能
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}