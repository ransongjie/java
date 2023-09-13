package com.xcrj.creation.factory_method;

// 具体工厂
public class FactoryB extends Factory {
    @Override
    public Product produce() {
        return new ProductB();
    }
}

