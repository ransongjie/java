package com.xcrj.creation.singleton.example0.lazy;

public class SingletonXcrj {
    // 静态成员属性，未赋值
    private static SingletonXcrj singletonXcrj;

    // 私有化构造器并且不对外提供setter，getter
    private SingletonXcrj() {

    }

    // 静态方法操作静态变量
    public static synchronized SingletonXcrj getSingleton() {
        return SingletonXcrj.singletonXcrj = new SingletonXcrj();
    }
}
