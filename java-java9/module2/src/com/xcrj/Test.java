package com.xcrj;

// 跨模块引入包
import com.xcrj01.Dog;

public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.dog();
    }
}