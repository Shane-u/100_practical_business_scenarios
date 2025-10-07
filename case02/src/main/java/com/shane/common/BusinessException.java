package com.shane.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:42
 * @Description:
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private String code;

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 加入异常的根本原因的构造方法
     *
     * @param code
     * @param message
     * @param cause
     */
    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
