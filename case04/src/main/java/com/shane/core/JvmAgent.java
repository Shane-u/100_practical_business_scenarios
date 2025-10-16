package com.shane.core;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

/**
 * @Author: Shane
 * @Date: 2025/10/10/20:49
 * @Description: 增强的Agent类，实现反射调用私有方法
 */
public class JvmAgent {

    // 默认只执行一次,二次附着不会再次触发 agentmain 方法
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("===== Agent已成功挂载到目标进程 =====");
        // 获取所有的加载类
        /*Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> cls : classes) {
            System.out.println("所有已经加载的类：" + cls.getName());
        }*/
        System.out.println("本次传递的参数：" + agentArgs);

        // 校验参数（确保是合法数字）
        if (agentArgs == null || !agentArgs.matches("\\d+")) {
            System.err.println("参数错误：请传递数字");
            return;
        }
        int targetUserId = Integer.parseInt(agentArgs);
        try {
            // 调用目标进程中私有方法
            callTargetClassPrivateMethod(instrumentation, targetUserId);
        } catch (Exception e) {
            System.err.println("反射调用私有方法失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 调用目标进程中私有方法
     * @param instrumentation
     * @throws Exception
     */
    private static void callTargetClassPrivateMethod(Instrumentation instrumentation, int targetUserId) throws Exception {
        // 遍历目标进程中所有已加载的类
        Class<?>[] loadedClasses = instrumentation.getAllLoadedClasses();
        Class<?> targetClass = null;

        // 查找目标类
        for (Class<?> cls : loadedClasses) {
            if (cls.getName().equals("com.shane.business.UserService")) {
                targetClass = cls;
                break;
            }
        }

        if (targetClass == null) {
            System.out.println("\n未找到目标类com.shane.business.UserService");
            return;
        }
        System.out.println("\n找到目标类：" + targetClass.getName());

        // 通过反射获取目标类（targetClass）的私有无参构造方法，并将其设置为可访问（setAccessible(true)）
        // 然后通过该构造方法创建目标类的实例（targetInstance）
        // 这样即使构造方法是私有的，也能实例化对象，为后续反射调用私有方法做准备。
        java.lang.reflect.Constructor<?> constructor = targetClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object targetInstance = constructor.newInstance();

        // 调用目标类的私有方法
        Method privateMethod = targetClass.getDeclaredMethod("getUserInfo", String.class);
        privateMethod.setAccessible(true);
        Object result = privateMethod.invoke(targetInstance, String.valueOf(targetUserId));

        System.out.println("调用目标进程中私有方法结果: " + result);
    }
}
