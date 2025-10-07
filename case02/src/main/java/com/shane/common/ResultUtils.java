package com.shane.common;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:52
 * @Description:
 */
public class ResultUtils {
    public static <T> Result<T> success() {
        return new Result<>(true, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(false, null, null, msg);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(false, null, code, msg);
    }
}
