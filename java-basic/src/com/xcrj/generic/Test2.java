package com.xcrj.generic;

// 泛型成员方法，在方法返回类型前声明泛型
public class Test2 {
    // xcrj 在返回类型后面声明泛型作用于入参类型
    public <T> void test(T name){
        System.out.println(name);
    }

    public static void main(String[] args) {
        Test2 test2=new Test2();
        // 显示指明泛型类型
        test2.<String>test("xcrj");
    }
}
