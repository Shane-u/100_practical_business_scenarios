package com.shane.service;


import java.net.SocketTimeoutException;

/**
 * @Author: Shane
 * @Date: 2025/10/08/15:22
 * @Description:
 */
public interface SmsService {

    /**
     * 发送业务消息
     *
     * @param msg
     */
    void sendSms(String msg) throws SocketTimeoutException; // 抛出异常被捕获

}
