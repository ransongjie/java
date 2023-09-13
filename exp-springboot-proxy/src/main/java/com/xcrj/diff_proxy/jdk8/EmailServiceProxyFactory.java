package com.xcrj.diff_proxy.jdk8;

import java.lang.reflect.Proxy;

public class EmailServiceProxyFactory {
    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                // 目标类的类加载器
                target.getClass().getClassLoader(),
                // 目标类接口，可以有多个
                target.getClass().getInterfaces(),
                // InvocationHandler
                new EmailInvocationHandler(target)
        );
    }
}
