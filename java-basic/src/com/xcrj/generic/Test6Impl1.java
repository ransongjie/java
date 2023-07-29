package com.xcrj.generic;

/**
 * 泛型实现：指明接口的泛型，减少了接口的泛型，实现类中仍然需要写未指明的泛型，当然也可以增加新的泛型
 * 指明接口泛型T, K为String
 * 实现父类泛型V, E
 * 声明更多泛型A, B
 */
public class Test6Impl1<V,E,A,B> implements Test6<String,String,V,E>  {

    @Override
    public String test(String nametImpl, String namekImpl, V namevImpl, E nameeImpl) {
        System.out.println(nametImpl);
        System.out.println(namekImpl);
        System.out.println(namevImpl);
        System.out.println(nameeImpl);
        return "";
    }

    public String test1(String nametImpl, String namekImpl, V namevImpl, E nameeImpl, A nameaImpl, B namebImpl) {
        System.out.println(nametImpl);
        System.out.println(namekImpl);
        System.out.println(namevImpl);
        System.out.println(nameeImpl);
        System.out.println(nameaImpl);
        System.out.println(namebImpl);
        return "";
    }

    public static void main(String[] args) {
        Test6Impl1<String,String,String,String> test6Impl1=new Test6Impl1<>();
        test6Impl1.test("xcrjString", "xcrjString", "xcrjvImpl", "xcrjeImpl");
        test6Impl1.test1("xcrjString", "xcrjString", "xcrjvImpl", "xcrjeImpl", "xcrjaImpl", "xcrjbImpl");
    }
}