package com.xcrj.diff_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EmailMethodInterceptor implements MethodInterceptor {
    /**
     * @param target      被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 调用方法之前，增强功能
        System.out.println("调用方法之前，增强功能");
        // cglib基于继承实现动态代理 invokeSuper 调用父类方法，result 父类方法返回值
        Object result = methodProxy.invokeSuper(target, args);
        // 调用方法之后，增强功能
        System.out.println("调用方法之后，增强功能");
        return result;
    }
}
