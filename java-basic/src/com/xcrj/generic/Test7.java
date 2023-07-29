package com.xcrj.generic;

import java.util.List;
import java.util.ArrayList;

/**
 * 泛型擦除，在使用时，继承时，实现时没有指定泛型的具体的类型，以Object进行处理
 * 编译时，进行泛型擦除，按Object处理
 * 编译时，进行泛型检查
 * 运行时，泛型都被替换为Object了，类信息仍在
 */
public class Test7 {
    public static void main(String[] args) {
        // 在使用时没有指定泛型的具体的类型，以Object进行处理
        List list=new ArrayList<String>();
        list.add("String");
        list.add(1);
        list.add(true);

        System.out.println("===================");
        for(Object obj:list){
            System.out.println(obj.getClass().getName());
            System.out.println(obj);
        }

        // 在使用时指定泛型的具体的类型，进行泛型检查
        List<String> list1=new ArrayList<String>();
        list1.add("String");
        // 泛型检查不通过
        // list1.add(1);
        // list1.add(true);
    }
}
