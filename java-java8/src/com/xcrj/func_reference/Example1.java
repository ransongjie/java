package com.xcrj.func_reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example1 {
    public static void main(String[] args) {
        test00();
        test01();
        test10();
        test11();
    }

    //list.forEach(方法引用)
    public static void test00() {
        List<String> list=new ArrayList<>();
        list.add("python");
        list.add("java");
        list.add("golang");
        list.add("c++");

        list.forEach(str->System.out.println(str));
        //引用已有方法 避免自己书写lambda表达式
        list.forEach(System.out::println);
    }

    //Arrays.sort(方法引用)
    public static void test01(){
        String[] strs=new String[]{"python","java","golang","c++"};
        Arrays.sort(strs,(a,b)->{return a.compareToIgnoreCase(b);});
        Arrays.sort(strs,(a,b)->a.compareToIgnoreCase(b));
        //引用已有方法 避免自己写lambda表达式
        Arrays.sort(strs,String::compareToIgnoreCase);
    }

    //自定义函数式接口，无参抽象方法
    public static void test10(){
        // 引用构造方法 类::new 
        GreetFunc greetFunc2=()->new GreetHello();
        greetFuncInterface(greetFunc2);
        // 引用已有方法 避免自己写lambda表达式
        greetFuncInterface(GreetHello::new);
        System.out.println("==========");

        // 引用静态方法 类名::静态方法
        GreetFunc greetFunc=()->{System.out.println("clzSayHi");};
        greetFuncInterface(greetFunc);
        // 引用已有方法 避免自己写lambda表达式
        greetFuncInterface(GreetHello::clzSayHi);
        System.out.println("==========");

        // 引用成员方法 对象::成员方法
        GreetFunc greetFunc1=()->{System.out.println("objSayHi");};
        greetFuncInterface(greetFunc1);
        // 引用已有方法 避免自己写lambda表达式
        GreetHello greet=new GreetHello();
        greetFuncInterface(greet::objSayHi);
        System.out.println("==========");
    }

    //自定义函数式接口，有参抽象方法
    public static void test11(){
        // 引用构造方法 类::new 
        HelloFunc helloFunc=(str)->{System.out.println(str);};
        helloFuncInterface(helloFunc,"xcrj");
        // 引用已有方法 避免自己写lambda表达式
        helloFuncInterface(GreetHello::new,"xcrj");
        System.out.println("==========");

        // 引用静态方法 类名::静态方法
        HelloFunc helloFunc1=(str)->{System.out.println(str);};
        helloFuncInterface(helloFunc1,"xcrj");
        // 引用已有方法 避免自己写lambda表达式
        helloFuncInterface(GreetHello::clzSayHi,"xcrj");
        System.out.println("==========");

        // 引用成员方法 对象::成员方法
        HelloFunc helloFunc2=(str)->{System.out.println(str);};
        helloFuncInterface(helloFunc2,"xcrj");
        // 引用已有方法 避免自己写lambda表达式
        GreetHello greet=new GreetHello();
        helloFuncInterface(greet::objSayHi,"xcrj");
        System.out.println("==========");
    }

    // greetFunc 方法接口
    public static void greetFuncInterface(GreetFunc greetFunc) {
        greetFunc.greet();
    }

    // helloFunc 方法接口
    public static void helloFuncInterface(HelloFunc helloFunc,String str) {
        helloFunc.hello(str);
    }
}
