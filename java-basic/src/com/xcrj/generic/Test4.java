package com.xcrj.generic;

// 泛型构造方法，在方法返回类型前声明泛型
public class Test4 {
    // xcrj 在返回类型后面声明泛型作用于入参类型
    public <T> Test4(T name){
        System.out.println(name instanceof String);
        System.out.println(name);
    }
    public static void main(String[] args) {
        // 显示指明泛型类型
        new <String>Test4("xcrj");
    }
}
