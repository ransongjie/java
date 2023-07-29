package com.xcrj.interface_default_func;

/**
 * 接口默认方法
 */
public interface MyInterface {
    default void defaultFunc() {
        System.out.println("default MyInterface");
    }
}
