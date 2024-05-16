package com.xzy.agent.agent;

import java.lang.instrument.Instrumentation;

/**
 * 代理程序
 *
 * @author xzy.xiao
 * @date 2024/5/16  11:34
 */
public class MyAgent {

    private final static String TARGET_CLASS = "com/xzy/agent/app/MyApp";

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("Java Agent loaded dynamically");

        instrumentation.addTransformer(new MyClassFileTransformer(), true);

        try {
            for (Class<?> loadedClass : instrumentation.getAllLoadedClasses()) {
                if (loadedClass.getName().equals(TARGET_CLASS)) {
                    /*
                     * 当 Java Agent 动态加载并附加到一个正在运行的 JVM 时，agentmain 方法会被调用。
                     * 此时，JVM 中已经加载了一些类。如果你希望对这些已经加载的类进行字节码修改，就需要
                     * 使用 retransformClasses 方法。
                     */
                    instrumentation.retransformClasses(loadedClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
