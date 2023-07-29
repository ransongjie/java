package com.xcrj.data_type;

//数据类型转换
public class TypeConvertion {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    //Integer>>Double
    public static void test1(){
        Integer i1=new Integer(10);
        Double d1=new Double(i1);
        System.out.println(d1);
    }

    //double>>Double
    public static void test2(){
        Double d1=new Double(20.1);
        Object o1=d1;
        System.out.println(d1);
        System.out.println(o1 instanceof Double);
    }

    //Double>>Double
    public static void test3(){
        Double d1=new Double(20.1);
        Double d2=new Double(d1);
        System.out.println(d2);
    }

    //Float>>Double
    public static void test4(){
        Float d1=new Float(20.1);
        Double d2=new Double(d1);
        System.out.println(d2);
    }
}