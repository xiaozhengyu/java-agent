# Java Agent



Java Agent 是一种在 Java 应用程序中用于实现监控、分析和动态修改应用程序行为的技术。Java Agent 通常使用 Java Instrumentation API，通过字节码操作来修改和增强 Java 应用程序的功能。



## 主要功能



1.  **字节码操作**：Java Agent 通过修改字节码，可以在不改变源代码的情况下改变应用程序的行为。这对于性能监控、安全审计和错误检测非常有用。
2.  **动态加载**：Java Agent 可以在 JVM 启动时加载，也可以在应用程序运行时动态附加到 JVM 上。这提供了极大的灵活性，允许在应用程序运行时进行监控和诊断。
3.  **Instrumentation API**：Java 提供了 java.lang.instrument 包，该包包含了一些类和接口，用于实现 Java Agent 的功能。其中最重要的是 `Instrumentation` 接口，它允许 Java Agent 修改类定义、获取类加载信息等。



## 创建 Java Agent



创建 Java Agent 主要包括编写一个实现 `premain` 或 `agentmain` 方法的类，并在 MANIFEST.MF 文件中指定该类。

1.  **实现 `premain` 方法**：在 JVM 启动时加载 Java Agent。

    ```java
    java复制代码public class MyAgent {
        public static void premain(String agentArgs, Instrumentation inst) {
            System.out.println("Java Agent loaded at JVM startup");
            // 进行字节码操作等处理
        }
    }
    ```

2.  **实现 `agentmain` 方法**：在应用程序运行时动态加载 Java Agent。

    ```java
    java复制代码public class MyAgent {
        public static void agentmain(String agentArgs, Instrumentation inst) {
            System.out.println("Java Agent loaded dynamically");
            // 进行字节码操作等处理
        }
    }
    ```

3.  **配置 MANIFEST.MF**：在 JAR 包的 MANIFEST.MF 文件中指定代理类。

    ```
    Manifest-Version: 1.0
    Premain-Class: com.example.MyAgent
    Agent-Class: com.example.MyAgent
    ```



## 使用 Java Agent



-   **在 JVM 启动时加载**：使用 `-javaagent` 参数启动 JVM，并指定 Java Agent 的 JAR 包。

    ```sh
    java -javaagent:path/to/agent.jar -jar myapp.jar
    ```

    

-   **在运行时动态加载**：使用 Attach API 将 Java Agent 动态附加到正在运行的 JVM。

    ```java
    import com.sun.tools.attach.VirtualMachine;
    
    public class AttachAgent {
        public static void main(String[] args) throws Exception {
            String pid = "12345"; // 目标 JVM 的进程 ID
            String agentPath = "path/to/agent.jar";
            
            VirtualMachine vm = VirtualMachine.attach(pid);
            vm.loadAgent(agentPath);
            vm.detach();
        }
    }
    
    ```

    

---

# 示例



## 01 - hello_world



演示 “在 JVM 启动时加载 Java Agent 程序” 的简单示例。



### 1.1 目标程序



### 1.2 代理程序



>   [!NOTE]
>
>   1.   com.xzy.agent.agent.MyClassFileTransformer#TARGET_CLASS 的值要与目标程序对应；
>   2.   需要在 pom.xml 中给出 MANIFIST.MF 文件的具体位置



### 1.3 测试



测试流程：

1.   打包目标程序，然后运行目标程序：

     ```shell
     java -cp my-app-1.0-SNAPSHOT.jar com.xzy.agent.app.MyApp
     ```

     控制台打印结果：

     ```
     hello world!
     ```

2.   打包代理程序，重新运行目标程序：

     ```shell
     java -javaagent:my-agent-1.0-SNAPSHOT.jar -cp my-app-1.0-SNAPSHOT.jar com.xzy.agent.app.MyApp
     ```

     控制台打印结果：

     ```
     Java Agent loaded at JVM startup
     Instrumenting com/xzy/agent/app/MyApp
     hello world!
     ```



## 02 - hello_world



演示在 “应用程序运行时动态加载 Java Agent” 的简单示例。



### 2.1 目标程序



### 2.2 代理程序



### 2.3 注入程序



### 2.4 测试



测试流程：

1.   启动目标程序：

     控制台打印结果：（每5秒打印一次 PID 以及 “hello world!” 字符串）

     ```
     ...
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     ...
     ```

2.   打包代理程序

3.   根据目标程序打印的 PID 以及代理程序的的 jar 包地址调整注入程序，启动注入程序：

     [注入程序]控制台打印结果：

     ```
     my-app pic：7976
     my-agent path:E:\StudyPlace\CodeRepository\java-agent\example\my-agent\target\my-agent-1.0-SNAPSHOT.jar
     ```

     [目标程序]控制台打印结果：

     ```
     ...
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     Java Agent loaded dynamically
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     PID：7976 ==> hello world!
     ...
     ```

     



# 资料

