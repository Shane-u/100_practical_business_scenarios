package com.shane.common;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:47
 * @Description:
 */
public class BusinessExceptionUtils {

    /**
     * 直接声明为静态方法可以方便用类名调用
     *
     * @param msg 消息提示
     * @return
     */
    public static BusinessException businessException(String msg) {
        return new BusinessException(null, msg);
    }

    /**
     * @param code 错误码
     * @param msg  消息提示
     * @return
     */
    public static BusinessException businessException(String code, String msg) {
        return new BusinessException(code, msg);
    }


    /**
     * @param code  错误码
     * @param msg   消息提示
     * @param cause 根本原因
     * @return
     */
    public static BusinessException businessException(String code, String msg, Throwable cause) {
        return new BusinessException(code, msg, cause);
    }
}
