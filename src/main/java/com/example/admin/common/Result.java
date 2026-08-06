package com.example.admin.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private T data;
    private Long timestamp; // 增加时间戳，方便前端排查接口响应延迟问题

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    // --- 成功相关 ---

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    // 支持自定义成功提示（比如：新增用户成功）
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // --- 失败相关 ---

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    // 支持直接传入自定义错误信息（比如：捕获到异常时，把异常信息返回给前端）
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.ERROR.getCode(), message, null);
    }

    // 支持自定义状态码和错误信息
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}