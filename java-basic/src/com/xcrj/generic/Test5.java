package com.xcrj.generic;

// 泛型类，T-type, K-Key, V-value, E-element
public class Test5<T,K,V,E> {
    public void test(T namet,K namek,V namev,E namee){
        System.out.println(namet);
        System.out.println(namek);
        System.out.println(namev);
        System.out.println(namee);
    }

    public static void main(String[] args) {
        Test5<String,String,String,String> test5=new Test5<>();
        test5.test("xcrjt","xcrjk","xcrjv","xcrje");
    }
}
