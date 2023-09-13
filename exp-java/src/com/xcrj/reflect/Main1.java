package com.xcrj.reflect;

/**
 * 获取Class对象的四种方式
 */
public class Main1 {
    public static void main(String[] args) {

    }

    // 类.class
    private static void test1() {
        Class appleClass = Apple.class;
    }

    // 对象.class
    private static void test3() {
        Apple apple = new Apple();
        Class appleClass = apple.getClass();
    }

    // Class.forName
    // Class对象不会被初始化，不执行<clinit>方法，静态变量 静态代码块
    private static void test2() {
        try {
            Class appleClass = Class.forName("com.xcrj.reflect.Apple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 类加载器.loadClass
    // Class对象不会被初始化，不执行<clinit>方法，静态变量 静态代码块
    private static void test4() {
        try {
            Class appleClass = ClassLoader.getSystemClassLoader().loadClass("com.xcrj.reflect.Apple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
