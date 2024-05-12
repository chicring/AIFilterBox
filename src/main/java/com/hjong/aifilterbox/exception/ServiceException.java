package com.hjong.aifilterbox.exception;

import com.hjong.aifilterbox.common.ErrorCode;
import lombok.Getter;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/
@Getter
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}