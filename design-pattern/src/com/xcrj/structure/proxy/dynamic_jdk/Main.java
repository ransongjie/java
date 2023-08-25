package com.xcrj.structure.proxy.dynamic_jdk;

import java.lang.reflect.Proxy;

/**
 * Subject, RealSubject, InvocationHandler, Proxy
 */
public class Main {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject = new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象；注意强制转换必须是接口
        Subject proxyClass = (Subject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);

        proxyClass.sellBooks();

        proxyClass.speak();
    }
}
