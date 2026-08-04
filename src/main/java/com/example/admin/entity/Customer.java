package com.example.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Lombok注解：自动生成Getter/Setter
@TableName("customers") // 对应数据库表名
public class Customer {

    /**
     * 主键ID
     * value = "customer_id" 对应数据库字段
     * type = IdType.AUTO 表示使用数据库自增策略
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    private String name;

    private String email;

    private String passwordHash;

    private LocalDateTime createdAt;
}