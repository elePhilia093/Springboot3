package com.example.admin.exception;

import com.example.admin.common.Result;
import com.example.admin.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j // 使用 Lombok 的日志注解
@RestControllerAdvice // 标识这是一个全局异常处理类，并自动返回 JSON
public class GlobalExceptionHandler {

    /**
     * 1. 处理自定义的业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        // 业务异常属于预期内的错误，记录 warn 级别日志即可
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 2. 处理自定义的系统异常
     */
    @ExceptionHandler(SystemException.class)
    public Result<?> handleSystemException(SystemException e) {
        // 系统异常属于严重错误，记录 error 级别日志，并打印堆栈
        log.error("系统异常: code={}, message={}", e.getCode(), e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 3. 兜底处理：捕获所有未被上面拦截的异常（如 NullPointerException, SQLException 等）
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        // 打印完整的异常堆栈，方便排查问题
        log.error("未知系统异常: ", e);
        // 生产环境中，为了防止暴露系统内部信息，通常返回统一的友好提示
        return Result.fail(ResultCode.ERROR);
    }
}