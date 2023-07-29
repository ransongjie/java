package com.xcrj.generic;

import java.util.List;
import java.util.ArrayList;

/**
 * 通配符
 * <?> 不能添加
 * <? extends Object> 不能添加
 * <? super ShangDongApple> 不能添加父对象
 * 实参类型List<? extends Apple>，形参类型List<? extends Fruit>
 * 实参不能添加list.add(new Fruit())，形参可以添加list.add(new Fruit())，产生了二义性，因此extends不允许添加
 * 实参类型List<? super Apple>，形参类型List<? super ShangDongApple>
 * 实参能添加本类对象list.add(new Apple())，实参能添加子类对象list.add(new ShangDongApple())，形参能添加本类对象list.add(new ShangDongApple())，形参能添加子类对象list.add(new YanTaiApple())
 */
public class Test8 {
    public static void main(String[] args) {
        // 不能添加
        List<?> list=new ArrayList<>();
        // list.add("Str");

        // 不能添加
        List<? extends Apple> list1=new ArrayList<>();
        // list1.add(new Fruit());
        // list1.add(new Apple());
        // list1.add(new ShangDongApple());

        testExtends(list1);
        // 传入更大范围
        testExtends1(list1);

        // 不能添加父对象
        List<? super Apple> list2=new ArrayList<>();
        // 可以添加本对象
        list2.add(new Apple());
        // 可以添加子对象
        list2.add(new ShangDongApple());
        // 不能添加父对象
        // list2.add(new Apple());
        
        testSuper(list2);
        // 传入更大范围
        testSuper1(list2);
    }

    public static void testExtends(List<? extends Apple> list){

    }

    // 实参类型List<? extends Apple>，形参类型List<? extends Fruit>
    // 实参不能添加list.add(new Fruit())，形参可以添加list.add(new Fruit())，产生了二义性，因此extends不允许添加
    public static void testExtends1(List<? extends Fruit> list){
        // list.add(new Fruit());
    }

    public static void testSuper(List<? super Apple> list){
        
    }

    // 实参类型List<? super Apple>，形参类型List<? super ShangDongApple>
    // 实参能添加本类对象list.add(new Apple())，实参能添加子类对象list.add(new ShangDongApple())，形参能添加本类对象list.add(new ShangDongApple())，形参能添加子类对象list.add(new YanTaiApple())
    public static void testSuper1(List<? super ShangDongApple> list){
        list.add(new ShangDongApple());
        list.add(new YanTaiApple());
    }
}
