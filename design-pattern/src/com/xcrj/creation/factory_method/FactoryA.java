package com.xcrj.creation.factory_method;

// 具体工厂
public class  FactoryA extends Factory{
    @Override
    public Product produce() {
        return new ProductA();
    }
}

