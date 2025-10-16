package com.shane.business;

/**
 * @Author: Shane
 * @Date: 2025/10/10/23:41
 * @Description: 目标业务类，包含私有方法
 */
public class UserService {

    private UserService() {
    }

    // 私有方法
    private String getUserInfo(String userId) {
        // 模拟业务逻辑
        return "用户ID: " + userId + ", 姓名: Shane";
    }

    // 私有静态方法
    private static String getSystemTime() {
        return "当前系统时间: " + System.currentTimeMillis();
    }
}
