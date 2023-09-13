package com.xcrj.diff_proxy.jdk8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EmailInvocationHandler implements InvocationHandler {
    // 目标对象=被代理对象
    private final Object target;

    public EmailInvocationHandler(Object target) {
        this.target = target;
    }

    // InvocationHandler invoke() 功能增强+调用目标类的目标方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用方法之前，增强功能
        System.out.println("调用方法之前，增强功能");
        // 调用目标类方法，result是返回值
        Object result = method.invoke(target, args);
        // 调用方法之后，增强功能
        System.out.println("调用方法之后，增强功能");
        return result;
    }
}
