package com.xcrj.creation.singleton.example0.hungry;

public class SingletonXcrj {
    // 静态成员属性，直接赋值
    private static SingletonXcrj singletonXcrj = new SingletonXcrj();

    // 私有化构造器并且不对外提供setter，getter
    private SingletonXcrj() {

    }

    // 静态方法，直接返回属性中创建的单例对象
    public static SingletonXcrj getSingleton() {
        return SingletonXcrj.singletonXcrj;
    }
}
