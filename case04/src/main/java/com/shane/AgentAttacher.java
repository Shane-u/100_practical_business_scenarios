package com.shane;


import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;
import java.util.Scanner;

/**
 * @Author: Shane
 * @Date: 2025/10/10/21:01
 * @Description:
 */
public class AgentAttacher {

    public static void main(String[] args) throws Exception {
        // 1. 检查是否提供了agent.jar路径，否则使用默认值1
        String agentPath = System.getProperty("user.dir") + "\\case04\\target\\case04-1.0-SNAPSHOT.jar";

        // 2. 获取所有正在运行的JVM进程
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();

        if (vms.isEmpty()) {
            System.out.println("未发现运行中的JVM进程");
            return;
        }


        // 3. 显示进程列表供用户选择
        System.out.println("发现以下JVM进程，请选择要附着的进程编号:");
        for (int i = 0; i < vms.size(); i++) {
            VirtualMachineDescriptor vmd = vms.get(i);
            System.out.printf("[%d] PID: %s, 名称: %s%n",
                    i, vmd.id(), vmd.displayName());
        }

        // 4. 读取用户选择
        Scanner scanner = new Scanner(System.in);
        int selectedIndex = -1;
        while (selectedIndex < 0 || selectedIndex >= vms.size()) {
            System.out.print("请输入编号: ");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效的数字");
            }
        }

        // 5. 附着到选中的进程并加载agent
        VirtualMachineDescriptor selectedVm = vms.get(selectedIndex);
        System.out.printf("正在附着到进程 %s (%s)...%n",
                selectedVm.id(), selectedVm.displayName());

        System.out.print("请输入要传递给Agent的参数：");
        String agentArgs = scanner.nextLine().trim();

        VirtualMachine vm = null;
        try {
            vm = VirtualMachine.attach(selectedVm);
            vm.loadAgent(agentPath,agentArgs);
            System.out.println("Agent加载成功");
        } catch (Exception e) {
            System.err.println("附着失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (vm != null) {
                try {
                    vm.detach();
                } catch (Exception ignore) {
                }
            }
        }
    }
}
