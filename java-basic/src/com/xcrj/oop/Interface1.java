package com.xcrj.oop;

public interface Interface1{
    public static final String name="xcrj";
    public abstract void getName();
    default void getNickName(){//接口默认方法 java8 新特性
        System.out.println("I am xcrj nick name");
    }
}