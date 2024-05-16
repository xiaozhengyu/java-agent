package com.xzy.agent.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author xzy.xiao
 * @date 2024/5/16  11:38
 */
public class MyClassFileTransformer implements ClassFileTransformer {

    private final static String TARGET_CLASS = "com/xzy/agent/app/MyApp";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // 插桩
        if (TARGET_CLASS.equals(className)) {
            // NOTE：可以借助ASM、Javassist等字节码操作库
            System.out.println("Instrumenting " + className);
        }

        return classfileBuffer;
    }

}
