package com.xcrj.reflect;

/**
 * 私有/共有属性和方法
 */
public class Person {
    //私有属性
    private String name = "Tom";
    //公有属性
    public int age = 18;

    //构造方法
    public Person() {
    }

    //有参构造
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //私有方法
    private void say() {
        System.out.println("private say()...");
    }

    //公有方法
    public void work() {
        System.out.println("public work()...");
    }
}
