package com.hjong.aifilterbox.exception;

import com.hjong.aifilterbox.common.ErrorCode;
import com.hjong.aifilterbox.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result<?> HandlerServiceException(ServiceException e) {
        log.error("ServiceException ", e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> HandlerRuntimeException(RuntimeException e) {
        log.error("RuntimeException ", e);
        return Result.fail(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
