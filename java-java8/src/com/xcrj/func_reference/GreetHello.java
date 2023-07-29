package com.xcrj.func_reference;

public class GreetHello {
    //构造无参方法
    public GreetHello(){
        System.out.println("constructorSayHi");
    }

    //构造有参方法
    public GreetHello(String v){
        System.out.println("constructorSayHi "+v);
    }

    //成员无参方法
    public void objSayHi(){
        System.out.println("objSayHi");
    }

    //成员有参方法
    public void objSayHi(String v){
        System.out.println("objSayHi "+v);
    }

    //静态无参方法
    public static void clzSayHi(){
        System.out.println("clzSayHi");
    }

    //静态有参方法
    public static void clzSayHi(String v){
        System.out.println("clzSayHi "+ v);
    }
}
