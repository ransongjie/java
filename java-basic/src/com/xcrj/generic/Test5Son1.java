package com.xcrj.generic;

/**
 * 泛型继承，指明父类的泛型，减少了父类的泛型，子类中仍然需要写未指明的泛型，当然也可以增加新的泛型
 * 指明父类泛型T, K为String
 * 继承父类泛型V, E
 * 声明更多泛型A, B
 */
public class Test5Son1<V,E,A,B> extends Test5<String,String,V,E> {
    public void test(String nametson,String namekson,V namevson,E nameeson,A nameason,B namebson){
        System.out.println(nametson);
        System.out.println(namekson);
        System.out.println(namevson);
        System.out.println(nameeson);
        System.out.println(nameason);
        System.out.println(namebson);
    }

    public static void main(String[] args) {
        Test5Son1<String,String,String,String> test5Son1=new Test5Son1<String,String,String,String>();
        test5Son1.test("xcrjString","xcrjString","xcrjvson","xcrjeson","xcrjason","xcrjbson");
    }
}
