package com.xcrj.oop;

public class MyClass extends AbstractClass1{

    @Override
    public void getSecondName() {
        super.setName("xcrj");
        System.out.println(super.getName());
        System.out.println("I am xcrj2");
    }

    public static void main(String[] args) {
        MyClass mc=new MyClass();
        mc.getSecondName();
    }
}
