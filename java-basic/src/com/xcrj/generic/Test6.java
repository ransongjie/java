package com.xcrj.generic;

// 泛型接口
public interface Test6<T,K,V,E> {

    // java8增强接口，default void方法
    default String test(T namet,K namek,V namev,E namee){
        System.out.println(namet);
        System.out.println(namek);
        System.out.println(namev);
        System.out.println(namee);
        return "";
    }

    // java8增强接口，default void方法
    public static void main(String[] args) {
        // 使用接口创建 匿名类对象
        Test6<String,String,String,String> test6=new Test6<String,String,String,String>() {};
        test6.test("xcrjt","xcrjk","xcrjv","xcrje");
    }
}
