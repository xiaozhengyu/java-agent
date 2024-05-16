package com.xzy.agent.app;

import java.lang.management.ManagementFactory;

/**
 * 目标程序
 *
 * @author xzy.xiao
 * @date 2024/5/16  11:36
 */
public class MyApp {

    public void saySomething(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws InterruptedException {
        MyApp myApp = new MyApp();

        while (true) {
            myApp.saySomething("PID：" + getPid() + " ==> hello world!");
            Thread.sleep(5000);
        }
    }

    public static String getPid() {
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();

        // JVM名称通常是以 "<pid>@<hostname>" 的格式提供
        return jvmName.split("@")[0];
    }
}
