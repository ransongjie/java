package com.xcrj.creation.singleton.example1.dcl;

/**
 * 双重检查加锁提高效率
 * 为什么叫双重检查，锁外检查一次，锁内检查一次
 * 私有化构造函数 volatile内存可见
 * 向外提供访问接口
 * 保证单例
 * 保证线程安全
 * double-check-locking
 */
public class Singleton01 {
    private volatile static Singleton01 singleton;

    private Singleton01() {
    }

    public static Singleton01 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton01.class) {
                if (singleton == null) {
                    singleton = new Singleton01();
                }
            }
        }
        return singleton;
    }

}
