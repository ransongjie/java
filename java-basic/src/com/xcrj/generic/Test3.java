package com.xcrj.generic;

// 泛型静态方法，在方法返回类型前声明泛型
public class Test3 {
    // xcrj 在返回类型后面声明泛型作用于入参类型
    public static <T> void test(T name){
        System.out.println(name);
    }
    public static void main(String[] args) {
        // 显示指明泛型类型
        Test3.<String>test("xcrj");
    }
}
