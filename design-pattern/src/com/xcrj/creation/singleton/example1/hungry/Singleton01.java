package com.xcrj.creation.singleton.example1.hungry;

/**
 * 使用静态变量保证线程安全和单例
 * 私有化构造函数，成员属性并不提供setter、getter
 * 向外提供访问接口
 * 保证单例
 * 保证线程安全
 * <p>
 * 类初始化过程
 * 父类类构造器<clinit>()
 * 子类类构造器<clinit>()
 */
public class Singleton01 {
    private static Singleton01 instance = new Singleton01();

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton01.getInstance();
    }
}
