package com.xcrj.creation.factory_method;

public class Main {
    public static void main(String[] args) {
        // 创建具体工厂A
        FactoryA mFactoryA = new FactoryA();
        // 生产具体产品A
        Product product1 = mFactoryA.produce();
        product1.getName();

        // 创建具体工厂B
        FactoryB mFactoryB = new FactoryB();
        // 生产具体产品B
        Product product2 = mFactoryB.produce();
        product2.getName();
    }
}
