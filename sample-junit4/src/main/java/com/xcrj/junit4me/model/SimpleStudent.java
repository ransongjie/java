package com.xcrj.junit4me.model;

public class SimpleStudent {
    public String sayHi(){
        return "hi";
    }

    public String sayHiParam(String name){
        System.out.println("hi "+name);
        return "hi "+name;
    }
}
