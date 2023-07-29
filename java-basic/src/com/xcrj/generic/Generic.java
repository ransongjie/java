package com.xcrj.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合
 * 静态函数
 * 泛型类 泛型继承 泛型属性 泛型方法 
 */
public class Generic{
    public static void main(String[] args) {
        

    }

    /**
     * 泛型集合
     * List<E> extends Collection<E>
     * 使用时指明具体的类型
     */
    public static void test1() {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);list.add(2);
    }

    /**
     * 静态函数 
     * 只能对入参使用泛型
     */
    public static <T> void name(T a) {
        System.out.println(a);
    }

    /**
     * 泛型类 泛型继承
     */
    class Fater<T,P,Q>{
        private T name;
    }
    class Son<P,Q,U> extends Fater<String,P,Q>{
        private P name;
    }

    /**
     * 泛型接口
     */
}