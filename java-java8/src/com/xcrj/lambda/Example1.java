package com.xcrj.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda表达式，作为 函数式接口 中 抽象方法 的方法体
 * 传入lambda表达式中的变量 必须是final或effectively final
 */
public class Example1 {
    public static void main(String[] args) {
        test00();
        test10(); 
        test20();
        test21();
    }

    /**
     * 无入参，无返回值
     */
    public static void test00() {
        PingPong pp = () -> System.out.println("pong");
        pingPongFuncInterface(pp);
    }

    /**
     * 有入参，有返回值
     * lambda表达式的4种写法
     */
    public static void test10() {
        Operation add = (double a, double b) -> {
            return a + b;
        };
        Operation sub = (a, b) -> {
            return a - b;
        };
        Operation mul = (double a, double b) -> a * b;
        Operation div = (a, b) -> a / b;

        operationFuncInterface(add, 10.0, 2.0);
        operationFuncInterface(sub, 10.0, 2.0);
        operationFuncInterface(mul, 10.0, 2.0);
        operationFuncInterface(div, 10.0, 2.0);
    }

    /**
     * 基本数据类型
     * 传入lambda表达式中的基本数据类型，变量值不能被修改
     */
    public static void test20() {
        // 显式final修饰
        final int v = 10;
        PingPong pingPong1 = () -> System.out.println(v);
        pingPongFuncInterface(pingPong1);

        // 隐式不被修改
        int v2 = 20;
        PingPong pingPong2 = () -> System.out.println(v2);
        pingPongFuncInterface(pingPong2);

        // 被修改，编译报错
        int v3 = 30;
        PingPong pingPong3 = () -> System.out.println(v3);
        pingPongFuncInterface(pingPong3);
        // v3被修改
        // v3=10;
    }

    /**
     * 引用数据类型
     * 传入lambda表达式中的引用数据类型，引用值（地址）不能被修改
     */
    public static void test21() {
        // 显式final修饰
        final List<String> list0 = new ArrayList<>();
        list0.add("a");
        PingPong pingPong3 = () -> System.out.println(list0);
        pingPongFuncInterface(pingPong3);
        list0.add("b");
        // final修饰不能被修改
        // list0=new ArrayList<>();

        // 隐式不被修改，list对象未改变
        List<String> list = new ArrayList<>();
        list.add("a");
        PingPong pingPong4 = () -> System.out.println(list);
        pingPongFuncInterface(pingPong4);
        list.add("b");

        // 被修改，编译报错
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        PingPong pingPong5 = () -> System.out.println(list2);
        pingPongFuncInterface(pingPong5);
        // list2被修改
        // list2=new ArrayList<>();
    }

    /**
     * 方法作为入参，函数式接口 作为方法入参，lambda表达式作函数式接口中抽象方法的方法体
     * 
     * @param pingPong 接口 函数式接口
     */
    public static void pingPongFuncInterface(PingPong pingPong) {
        // 接口调用抽象方法，抽象方法方法体 由lambda表达式提供
        pingPong.pong();
    }

    /**
     * 
     * @param operation 接口 函数式接口
     * @param a
     * @param b
     */
    public static void operationFuncInterface(Operation operation, double a, double b) {
        System.out.println(operation.operate(a, b));
    }
}