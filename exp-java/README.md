# cpu/由java导致的CPU飙高如何定位

```shell
# 编写代码
vim Test.java
# 编译到当前目录下
javac -d . Test.java
# 运行
java -classpath . com.xcrj.Test
# 定位CPU飙高 进程PID
top
# 定位 线程PID
top -H -p 进程PID
# 线程PID转十六进制
printf '0x%x\n' 线程PID
#  定位java代码
jstack 进程PID | grep 十六进制线程PID -A 20
```

Test.java

```java
package com.xcrj;

public class Test {
    public static void main(String[] args) {
        while (true) {
            ;
        }
    }
}
```

# sync/synchronized的三种使用方式

1. synchronized 方法 成员/方法
2. synchronized 代码块 静态对象/普通对象 用的多
3. synchronized 代码块 this/.class

# stop_thread_pool/安全的停止线程池

```text
threadPool.shutdown();
- 执行中的任务，执行完毕
- 再提交的任务，抛出异常 RejectedExecutionException

threadPool.isShutdown() 线程池是否调用了shutdown()
- true: 是调用shutdown()
- false: 否调用shutdown()

threadPool.isTerminated()
- 必须先调用threadPool.shutdown() 否则一直是false
- true: 所有任务是执行完毕
- false: 所有任务否执行完毕

threadPool.awaitTermination(10L, TimeUnit.SECONDS)
- 必须先调用threadPool.shutdown() 否则一直是false
- 返回 (所有任务是否执行完毕||达到timeout||interrupted)
- true: 所有任务执行完毕
- false: 所有任务否执行完毕||达到timeout||interrupted

threadPool.shutdownNow()
- 执行中的任务，interrupt
- 队列中的任务，作返回值
```

# stop_thread/安全的停止线程