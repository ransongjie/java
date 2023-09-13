package com.xcrj.creation.factory_abstract.example0.carfactory;

public class CarFactoryProducer {

    public static CarFactory produceDaZhongCarFactory() {
        return new DaZhongCarFactory();
    }

    public static CarFactory producePorscheCarFactory() {
        return new PorscheCarFactory();
    }
}
