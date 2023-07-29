package com.xcrj.interface_default_func;

/**
 * 接口默认方法
 * - 为什么出现？接口增加抽象方法，修改抽象方法之后，所有的实现类都需要修改，破坏了向下兼容
 * - 类 implements接口，可以@Override default方法
 * - 类 extends类 implements接口，优先调用继承类的方法，类优先
 * - 类 implements接口1,接口2，必须@Override 相同的default方法
 */
public class Example1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    /**
     * 实现类调用接口默认方法
     */
    public static void test1() {
        ImplClass implClass = new ImplClass();
        implClass.defaultFunc();
    }

    /**
     * 实现类重写接口默认方法
     * 类 implements接口，可以@Override default方法
     */
    public static void test2() {
        ImplClass1 implClass1 = new ImplClass1();
        implClass1.defaultFunc();
    }

    /**
     * 类优先，优先调用继承类的默认方法
     * 类 extends类 implements接口，优先调用继承类的方法，类优先
     */
    public static void test3() {
        ImplClass2 implClass2 = new ImplClass2();
        implClass2.defaultFunc();
    }

    /**
     * 类 implements接口1,接口2，必须@Override 相同的default方法
     */
    public static void test4() {
        ImplClass3 implClass3 = new ImplClass3();
        implClass3.defaultFunc();
    }
}
