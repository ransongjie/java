package com.xcrj.oop;

public class MyClass2 implements Interface1{

    @Override
    public void getName() {
       System.out.println("I am "+Interface1.name);
    }
    
    public static void main(String[] args) {
        MyClass2 mc2=new MyClass2();
        mc2.getName();
    }
}
