package com.example.admin.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private Long id;

    private String username;

    private String nickname;

    private String email;

    private String phone;

    private Integer status;

    private Integer deptId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
