package com.shane;

/**
 * @Author: Shane
 * @Date: 2025/10/10/23:50
 * @Description: 测试用的目标进程，启动后会一直运行，等待Agent附着
 */
public class TestApplication {
    // 目标进程二次附着不会重新加载 JvmAgent 类
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== 目标进程已启动 =====");
        System.out.println("进程ID: " + getProcessId());
        System.out.println("等待Agent附着...\n");

        // 循环保持进程运行，同时定期创建UserService实例 -> 确保类被加载
        while (true) {
            // 触发UserService类加载
            try {
                Class.forName("com.shane.business.UserService");
            } catch (ClassNotFoundException e) {
                System.out.println("UserService类未找到");
            }
            Thread.sleep(5000);
        }
    }

    // 获取当前进程ID
    private static String getProcessId() {
        // JVM进程ID通常在系统属性中，格式为"pid@hostname"
        String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return processName.split("@")[0];
    }
}
