# spin_lock自旋锁
SpinLock 可重入锁

TricketLock 公平锁 
- 缺点：serviceNum多处理缓存同步次数频繁

CLHLock 公平锁 
- 自旋：在前继结点locked字段上自旋，等待前继结点释放锁（pre.locked=true）
- 获取锁：设置locked=true, 前面结点释放锁, 后继结点获取锁
- 释放锁：设置locked=false, 从链表中移除当前结点

MCSLock 公平锁 
- 自旋：在当前结点locked字段上自旋，等待前继结点通知后继结点获取锁（cur.locked=true）
- 获取锁：设置当前结点locked=false，自旋等待前继结点通知后继结点获取锁（设置后继结点locked=true）
- 释放锁：设置后继结点locked=true，设置当前结点next=null

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
# 定位 线程PID, -H 以线程视角查看进程信息
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

- synchronized 方法 成员方法，静态方法
- synchronized 代码块 静态对象/普通对象
- synchronized 代码块 this/.class
- synchronized重点关注是否锁住了同1个对象

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
