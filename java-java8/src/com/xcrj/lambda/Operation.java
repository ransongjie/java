package com.xcrj.lambda;

// 有入参 有返回值
@FunctionalInterface
public interface Operation {
    double operate(double a,double b);
}
