package com.xcrj.generic;

// 泛型成员属性，需要在类名后面声明泛型
public class Test1<T>{
    private T name;
    public Test1(T name){
        this.name=name;
    }
    public static void main(String[] args) {
        Test1<String> test1=new Test1<>("xcrj");
        System.out.println(test1.name);
    }
}