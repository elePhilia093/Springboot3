package com.example.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserQueryDTO {


    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
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
    @NotBlank(message = "手机号不能为空")
    private String phone;


    /**
     * 状态
     * 1正常 0禁用
     */
    @NotBlank(message = "手机号不能为空")
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

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页显示记录数
     */
    private Integer pageSize = 10;

}