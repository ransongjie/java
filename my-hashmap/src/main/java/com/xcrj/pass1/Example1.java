package com.xcrj.pass1;

public class Example1 {
    public static void main(String[] args){
        test1();
        test2();
    }

    // 无参构造
    public static void test1(){
        SelfHashMap<String,Integer> selfHashMap=new SelfHashMap<>();
        for(int i=0;i<100;i++){
            selfHashMap.put("xcrj"+i, i);
        }

        System.out.println(selfHashMap.size());
    }

    // 有参构造
    public static void test2(){
        // size>=LoadFactory*capacity(length) 扩容, 
        // 75 = 0.75*100, 将扩容
        // 75 < 0.75*101, 不扩容
        SelfHashMap<String,Integer> selfHashMap=new SelfHashMap<>(101);
        for(int i=0;i<75;i++){
            selfHashMap.put("xcrj"+i, i);
        }

        System.out.println(selfHashMap.size());
    }
}
