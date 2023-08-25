package com.xcrj.creation.abstract_factory.example0.carfactory;

public class CarFactoryProducer {

    public static CarFactory produceDaZhongCarFactory() {
        return new DaZhongCarFactory();
    }

    public static CarFactory producePorscheCarFactory() {
        return new PorscheCarFactory();
    }
}
