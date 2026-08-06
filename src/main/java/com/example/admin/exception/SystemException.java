package com.example.admin.exception;

import com.example.admin.common.ResultCode;
import lombok.Getter;

@Getter
public class SystemException extends RuntimeException {
    private final Integer code;

    public SystemException(String message) {
        super(message);
        this.code = ResultCode.ERROR.getCode();
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}