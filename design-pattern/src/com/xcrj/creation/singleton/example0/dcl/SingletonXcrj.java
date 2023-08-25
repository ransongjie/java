package com.xcrj.creation.singleton.example0.dcl;

public class SingletonXcrj {
    // 静态成员属性，未赋值
    // volatile关键字保证 变量的可见性和有序性
    private volatile static SingletonXcrj singletonXcrj;

    // 私有化构造器并且不对外提供setter，getter
    private SingletonXcrj() {
    }

    // 静态方法操作静态属性
    public static SingletonXcrj getSingleton() {
        // 第1重检查，已经存在直接返回
        if (SingletonXcrj.singletonXcrj == null) {
            // 加锁，再检查
            synchronized (SingletonXcrj.class) {
                // 第2重检查
                if (SingletonXcrj.singletonXcrj == null) {
                    return SingletonXcrj.singletonXcrj = new SingletonXcrj();
                }
            }
        }

        return SingletonXcrj.singletonXcrj;
    }
}
