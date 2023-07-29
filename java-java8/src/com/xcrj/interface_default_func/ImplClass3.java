package com.xcrj.interface_default_func;
// 类实现多个接口，必须重写相同的default方法
public class ImplClass3 implements MyInterface, MyInterface1{

    @Override
    public void defaultFunc() {
        System.out.println("default ImplClass3");
    }
    
}
