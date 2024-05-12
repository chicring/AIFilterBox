package com.hjong.aifilterbox.common;

import lombok.Data;

/**
 * @author HJong
 * @version 1.0
 * @date 2024/5/10
 **/
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ok() {
        return restResult(null, 200, "操作成功");
    }

    public static <T> Result<T> ok(T data) {
        return restResult(data, 200, "操作成功");
    }

    public static <T> Result<T> ok(String msg) {
        return restResult(null, 200, msg);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return restResult(data, 200, msg);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return restResult(null, code, "操作失败");
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        return restResult(null, errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String msg) {
        return restResult(null, errorCode.getCode(), msg);
    }


    private static <T> Result<T> restResult(T data, int code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
}
