package com.xcrj.creation.singleton.example0.enums;

/*
 * 过程：
 * 类的主动使用，导致类加载阶段执行<clinit>()方法调用私有化的构造方法初始化SINGLETON静态变量
 * */
public enum SingletonHolder {
    // 枚举量默认public static final
    SINGLETON;
    private SingletonResource singletonResource;

    // 枚举构造函数默认private
    SingletonHolder() {
        this.singletonResource = new SingletonResource();
        System.out.println("1234");
    }

    // 未要求静态方法
    public SingletonResource getSingletonResource() {
        return SINGLETON.singletonResource;
    }
}
