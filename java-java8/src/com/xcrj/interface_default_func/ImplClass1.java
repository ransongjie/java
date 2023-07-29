package com.xcrj.interface_default_func;

/**
 * 含有默认方法的接口的实现类
 */
public class ImplClass1 implements MyInterface {

    @Override
    public void defaultFunc() {
        System.out.println("default ImplClass1");
    }

}
