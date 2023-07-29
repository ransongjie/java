package com.xcrj.test;

public class StaticFinal1 {
    //编译期优化
    static final String name2="xcrj";
    
    //静态常量 必须在类初始化或之前赋值
    static final String nickName;
    static{
        nickName="xcrj";
    }

    //编译期优化
    final String name3="xcrj";

    //一般常量 在对象初始化时赋值
    final String name;
    //java 中 final是最终量，被赋值一次之后不可以修改，声明与赋值可以分开
    //在 构造方法中 为常量赋值
    public StaticFinal1(){
        name="xcrj";
    }

    public static void main(String[] args) {
        StaticFinal1 final1=new StaticFinal1();
        System.out.println(final1.name);
    }
}
