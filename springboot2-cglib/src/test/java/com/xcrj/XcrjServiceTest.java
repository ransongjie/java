package com.xcrj;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

public class XcrjServiceTest {
    //测试 CGLIB 方法增强
    @Test
    public void testCGLIB() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(XcrjService.class);//要求非final父类
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("前置增强");
            Object ret = proxy.invokeSuper(obj, args);//
            System.out.println("后置增强");
            return ret;
        });
        //proxy是 XcrjService的子类
        XcrjService proxy = (XcrjService) enhancer.create();//
        proxy.hello();
    }
}
