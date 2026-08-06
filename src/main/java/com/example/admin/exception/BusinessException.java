package com.example.admin.exception;

import com.example.admin.common.ResultCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;

    public BusinessException(String message) {
        super(message);
        this.code = ResultCode.ERROR.getCode(); // 默认使用通用错误码
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    // 支持直接传入 ResultCode 枚举
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
}