package com.example.admin.common;

import lombok.Data;
import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
@Data
public class Result<T> implements Serializable {

    private int code;      // 状态码：200成功，500失败，或其他业务码
    private String message; // 提示信息
    private T data;        // 具体数据（泛型支持任意对象）

    // 私有构造方法，强制使用静态工厂方法创建
    private Result() {}

    /**
     * 成功 - 无数据
     */
    public static <T> Result<T> success() {
        return result(200, "操作成功", null);
    }

    /**
     * 成功 - 带数据
     */
    public static <T> Result<T> success(T data) {
        return result(200, "操作成功", data);
    }

    /**
     * 成功 - 自定义消息和数据
     */
    public static <T> Result<T> success(String message, T data) {
        return result(200, message, data);
    }

    /**
     * 失败 - 默认消息
     */
    public static <T> Result<T> fail() {
        return result(500, "操作失败", null);
    }

    /**
     * 失败 - 自定义消息
     */
    public static <T> Result<T> fail(String message) {
        return result(500, message, null);
    }

    /**
     * 失败 - 自定义状态码和消息
     */
    public static <T> Result<T> fail(int code, String message) {
        return result(code, message, null);
    }

    /**
     * 核心构建方法
     */
    private static <T> Result<T> result(int code, String message, T data) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMessage(message);
        res.setData(data);
        return res;
    }
}