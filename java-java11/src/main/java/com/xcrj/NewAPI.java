package com.xcrj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//增强API
public class NewAPI{
    public static void main(String[] args) throws Exception{
        test5();
    }

    /**
     * 集合，of()方法创建对象
     * List.of(), add()将抛出uoe
     * Set.of(), add()将抛出uoe, 创建重复元素值将报错
     */
    public static void test1(){
        // old
        List<String> list=new ArrayList<>();
        list.add("xcrj");
        list.add("xcrj1");
        list.add("xcrj2");
        System.out.println(list);

        // old 创建 list
        List<String> list3=Arrays.asList("xcrj","xcrj1","xcrj2");
        System.out.println(list3);
        //java.lang.UnsupportedOperationException
        //因为，Arrays.asList方法返回的是 Arrays.ArrayList 内部类，这个内部类的add()方法直接抛出uoe了
        // list3.add("xcrj3");

        // new 创建 set
        List<String> list2=List.of("xcrj","xcrj1","xcrj2");
        System.out.println(list2);
        //java.lang.UnsupportedOperationException
        //因为，List.of方法返回的是 AbstractImmutableList类的实现，这个类的add()方法直接抛出uoe了
        // list2.add("xcrj3");

        // new 创建 set
        // java.lang.IllegalArgumentException: duplicate element: xcrj2
        // Set<String> set=Set.of("xcrj","xcrj1","xcrj2","xcrj2");
        // System.out.println(set);

        // 延伸，新日期时间 of() 方法
        LocalDate ld=LocalDate.of(2023, 3, 13);
        System.out.println(ld);

        // 延伸，Stream of() 方法
        Stream<Integer> set=Stream.of(1,2,3);
        System.out.println(set);
    }

    /**
     * Stream API
     */
    public static void test2(){
        Stream<Integer> stream=Stream.of(1,2,3,4);
        stream.forEach(System.out::println);
        
        //
        Stream<Integer> stream1=Stream.of();
        stream1.forEach(System.out::println);

        // 不可以传入null, NPE 异常
        // Stream<Integer> stream2=Stream.of(null);
        // stream2.forEach(System.out::println);

        // 可以传入null
        Stream<Object> stream3=Stream.ofNullable(null);
        stream3.forEach(System.out::println);

        // 测试 takeWile
        // xcrj 流不能复用
        Stream<Integer> stream4=Stream.of(1,2,3,4,5,6);
        // 只输出1
        // take奇数, 丢弃偶数
        // xcrj 一旦为false，就终止take
        Stream<Integer> stream5=stream4.takeWhile(t->t%2!=0);
        System.out.println("============");
        stream5.forEach(System.out::println);

        // 测试 dropWile
        Stream<Integer> stream6=Stream.of(1,2,3,4,5,6);
        // 只输出1
        // drop奇数, 丢弃偶数
        // xcrj 一旦为false，就终止drop
        Stream<Integer> stream7=stream6.dropWhile(t->t%2!=0);
        System.out.println("============");
        stream7.forEach(System.out::println);

        // 无限流
        // stream iterate, 1作为初始值, param2是单步运算
        Stream<Integer> stream8=Stream.iterate(1, t->2*t+1);
        // 无限打印
        // stream8.forEach(System.out::println);
        stream8.limit(10).forEach(System.out::println);

        // 有限流
        Stream<Integer> stream9=Stream.iterate(1, t->t<1000,t->2*t+1);
        System.out.println("============");
        stream9.forEach(System.out::println);
    }

    /**
     * String API
     * @throws IOException
     */
    public static void test3() throws IOException{
        // 空白判断
        String str=" \t \r\n ";
        System.out.println(str.isBlank());

        // 去除字符串首尾空白
        String str1=" xcrj ";
        // xcrj 可以去除各种语言的空白字符
        // xcrj 增强了trim(), trim()只针对英文空白字符
        String str2=str1.strip();
        System.out.println(str2);
        // 4
        System.out.println(str2.length());

        // xcrj 中文空白字符也是一个字符
        String str3=" xcrj　";// "英文空格xcrj中文空格(全角空格)"
        // xcrj 只能去除unicode编码 < U+0020 的空白字符
        // xcrj  简单来说只能去除 英文空格
        String str4=str3.trim();
        System.out.println(str4);
        System.out.println(str4.length());
        // 

        String str5=" xcrj　";// "英文空格xcrj中文空格(全角空格)"
        // xcrj 只能去除unicode编码 < U+0020 的空白字符
        // xcrj  简单来说只能去除 英文空格
        String str6=str5.stripTrailing();
        System.out.println(str6);
        System.out.println(str6.length());
        String str7=str5.stripLeading();
        System.out.println(str7);
        System.out.println(str7.length());

        //
        String str8="xcrj".repeat(3);
        System.out.println(str8);

        //字符串按行 分割为stream流
        String str9="xcrj\r\nxcrj\r\n";
        str9.lines().forEach(System.out::println);

        //方便InputStream 读取文件内容，代替BufferReader
        FileInputStream fis=new FileInputStream("LICENCE");
        byte[]buf=new byte[fis.available()];
        fis.read(buf);
        fis.close();
        String str10=new String(buf);
        str10.lines().forEach(System.out::println);
    }

    /**
     * Optional API
     */
    public static void test4(){
        // NPE 异常
        // Optional<String> optional=Optional.of(null);
        // 
        Optional<String> optional1=Optional.ofNullable(null);
        String str=optional1.orElse("xcrj");
        System.out.println(str);

        // java.util.NoSuchElementException: No value present
        // String str1=optional1.orElseThrow();
    }

    /**
     * InputStream API
     * 文件复制
     */
    public static void test5() throws Exception{
        var is=new FileInputStream("xcrj.txt");
        try(var os=new FileOutputStream("xcrj2.txt")){
            is.transferTo(os);
        }
        is.close();
    }
}