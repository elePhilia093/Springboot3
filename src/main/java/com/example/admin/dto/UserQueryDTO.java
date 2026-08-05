package com.example.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryDTO {


    /**
     * 用户名
     */
    private String username;


    /**
     * 昵称
     */
    private String nickname;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 手机号
     */
    private String phone;


    /**
     * 状态
     * 1正常 0禁用
     */
    private Integer status;


    /**
     * 性别
     */
    private String gender;


    /**
     * 部门ID
     */
    private Integer deptId;


    /**
     * 最小年龄
     */
    private Integer minAge;


    /**
     * 最大年龄
     */
    private Integer maxAge;


    /**
     * 开始时间
     */
    private LocalDateTime startTime;


    /**
     * 结束时间
     */
    private LocalDateTime endTime;

}