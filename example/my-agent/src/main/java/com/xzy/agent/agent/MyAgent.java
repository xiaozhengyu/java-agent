package com.xzy.agent.agent;

import java.lang.instrument.Instrumentation;

/**
 * 代理程序
 *
 * @author xzy.xiao
 * @date 2024/5/16  11:34
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("Java Agent loaded at JVM startup");
        instrumentation.addTransformer(new MyClassFileTransformer());
    }

}
