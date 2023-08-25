package com.xcrj.creation.singleton.example1.enums;

/**
 * 枚举编译成字节码时会成为class
 * 其中的变量都是 public static final
 * 构造方法是private的
 * 枚举单例模式通过静态变量保证线程安全，也保证的反序列化的单例
 * <p>
 * 向外提供访问接口
 * 保证单例
 * 保证线程安全
 * 保证反序列化单例
 */
public enum Singleton01 {
    INSTANCE;
    private Resource instance;

    Singleton01() {
        instance = new Resource();
    }

    public Resource getInstance() {
        return instance;
    }
}

class Resource {

}
