package com.example.admin.common;

import lombok.Getter;

@Getter
public enum ResultCode {


    SUCCESS(200,"操作成功"),


    ERROR(500,"系统异常"),


    PARAM_ERROR(400,"参数错误"),


    UNAUTHORIZED(401,"未登录"),


    FORBIDDEN(403,"没有权限"),

    USER_NOT_EXIST(10000, "用户不存在"),

    USER_EXIST(10001, "用户名已存在");



    private final Integer code;


    private final String message;



    ResultCode(Integer code,String message){

        this.code = code;

        this.message = message;

    }


}