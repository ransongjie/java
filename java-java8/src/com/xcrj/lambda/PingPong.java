package com.xcrj.lambda;

// 无入参 无返回值
@FunctionalInterface
public interface PingPong {
    void pong();
    // 函数式接口中 只能有1个抽象方法
    // void ping(String v);
}

