package com.xcrj.creation.singleton.example0.lazy2;

public class SingletonXcrj {
    // 私有化构造器并且不对外提供setter，getter
    private SingletonXcrj() {
    }

    // 静态内部类中持有静态常量
    private static class SingletonHolder {
        private static final SingletonXcrj INSTANCE = new SingletonXcrj();
    }

    // 静态方法操作静态变量
    public static SingletonXcrj getSingleton() {
        return SingletonHolder.INSTANCE;
    }

}
