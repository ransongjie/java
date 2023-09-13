//package com.xcrj.structure.proxy.dynamic_cglib;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
//public class MyMethodInterceptor implements MethodInterceptor {
//    private Object target;
//
//    public MyMethodInterceptor(Object target) {
//        this.target = target;
//    }
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//        System.out.println("###   before invocation");
//        Object result = method.invoke(target, args);
//        System.out.println("###   end invocation");
//        return result;
//    }
//
//    public static Object getProxy(Object target) {
//        Enhancer enhancer = new Enhancer();
//        // 设置需要代理的对象
//        enhancer.setSuperclass(target.getClass());
//        // 设置代理人
//        enhancer.setCallback(new MyMethodInterceptor(target));
//        return enhancer.create();
//    }
//}
