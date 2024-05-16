package com.xzy.agent.app;

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

    public static void main(String[] args) {
        new MyApp().saySomething("hello world!");
    }

}
