package com.xcrj.creation.singleton.example1.lazy;

/**
 * 一个类的静态变量只有在类的首次的主动的真正的使用时才会加载
 * 静态变量在连接-准备阶段分配存储空间，在初始化阶段赋值
 * 静态变量只会被赋值一次，后面如果不提供构造方法和setter、getter方法，静态变量就是同一个
 * <p>
 * <p>
 * 私有化构造函数，成员属性并不提供setter、getter
 * 向外提供访问接口
 * 保证单例
 * 保证线程安全
 * 使用静态内部类保证懒加载
 * <p>
 * 类初始化过程
 * 父类类构造器<clinit>()
 * 子类类构造器<clinit>()
 * 内部类类构造器<clinit>()
 */
public class Singleton01 {
    private static class SingletonHolder {
        private static final Singleton01 INSTANCE = new Singleton01();
    }

    private Singleton01() {
    }

    //final含义 不允许重写
    public static final Singleton01 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton01.getInstance();
    }
}
