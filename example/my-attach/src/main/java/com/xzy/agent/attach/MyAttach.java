package com.xzy.agent.attach;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author xzy.xiao
 * @date 2024/5/16  16:10
 */
public class MyAttach {
    public static void main(String[] args) {
        String appPid = "7976";
        String agentPath = "E:\\StudyPlace\\CodeRepository\\java-agent\\example\\my-agent\\target\\my-agent-1.0-SNAPSHOT.jar";

        System.out.println("my-app pic：" + appPid);
        System.out.println("my-agent path:" + agentPath);

        try {
            VirtualMachine vm = VirtualMachine.attach(appPid);
            vm.loadAgent(agentPath);
            vm.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
