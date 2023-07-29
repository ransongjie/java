package com.xcrj.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * stream()/parallelStream()操作将集合打散成1个个元素，后面可以对每个元素进行操作
 * map()
 * filter()
 * collect(Collectors.xxx)
 * ...sql中能有的操作，流式编程基本都有...
 */
public class Example1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * 串行流
     * list.stream()
     */
    public static void test1() {
        List<String> list=new ArrayList<>();
        list.add("java");
        list.add("python");
        list.add("golang");
        list.add("c++");

        // 串行流
        List<String> list2=list.stream().map(String::toUpperCase).collect(Collectors.toList());
        // forEach迭代
        list2.forEach(System.out::println);
    }

    /**
     * 并行流
     * list.parallelStream()
     */
    public static void test2() {
        List<String> list=new ArrayList<>();
        list.add("java");
        list.add("python");
        list.add("golang");
        list.add("c++");

        // 并行流
        List<String> list2=list.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        // forEach迭代
        list2.forEach(System.out::println);
    }

    /**
     * List 转 Map
     */
    public static void test3() {
        List<Student> stus=new ArrayList<>();
        stus.add(new Student("xcrj0",0));
        stus.add(new Student("xcrj1",1));
        stus.add(new Student("xcrj2",2));
        stus.add(new Student("xcrj3",3));
        Map<String,Integer> map=stus.stream()
            .collect(Collectors.toMap(Student::getName, Student::getAge));
        map.entrySet().forEach(System.out::println);
    }
}