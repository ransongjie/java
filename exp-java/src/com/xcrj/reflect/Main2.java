package com.xcrj.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射操作Apple类
 * 可以获取私有方法和属性
 * 关闭安全检查，才能使用私有方法和属性
 */
public class Main2 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取class对象
        Class<?> appleClass = Class.forName("com.xcrj.reflect.Apple");
        // 使用class对象创建类实例
        Apple apple = (Apple) appleClass.newInstance();

        testMethod(appleClass, apple);

        testField(appleClass, apple);
    }

    private static void testMethod(Class<?> appleClass, Apple apple) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取所有方法，包括私有方法
        Method[] methods = appleClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // 获取public方法
        Method publicMethod = appleClass.getDeclaredMethod("publicMethod", String.class);
        // 调用方法
        publicMethod.invoke(apple, "FujiApple");

        // 获取private方法
        Method privateMethod = appleClass.getDeclaredMethod("privateMethod");
        // 取消private成员方法安全检查，才能调用private方法
        privateMethod.setAccessible(true);
        // 调用方法
        privateMethod.invoke(apple);
    }

    private static void testField(Class<?> appleClass, Apple apple) throws NoSuchFieldException, IllegalAccessException {
        // 获取所有属性，包括私有属性
        Field[] fields = appleClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        // 获取public属性
        Field publicField = appleClass.getDeclaredField("name");
        // 设置属性值
        publicField.set(apple, "FujiApple");
        System.out.println(apple.name);

        // 获取private属性
        Field privateField = appleClass.getDeclaredField("value");
        // 取消private属性安全检查，才能修改参数
        privateField.setAccessible(true);
        // 设置属性值
        privateField.set(apple, "10元");
        System.out.println(apple.getValue());
    }
}
