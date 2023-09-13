package com.xcrj.creation.factory_abstract.example0;

import com.xcrj.creation.factory_abstract.example0.carfactory.CarFactory;
import com.xcrj.creation.factory_abstract.example0.carfactory.CarFactoryProducer;
import com.xcrj.creation.factory_abstract.example0.dazhong.DaZhong;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche;

/**
 * 工厂建造者
 * 大众汽车工厂，保时捷汽车工厂
 * 捷达汽车，帕萨特汽车，桑塔纳汽车。保时捷718，保时捷918，保时捷911
 */
public class Main {
    public static void main(String[] args) {
        // 创建大众汽车系列工厂
        CarFactory daZhongCarFactory = CarFactoryProducer.produceDaZhongCarFactory();
        // 创建大众系列产品
        DaZhong jieDa = daZhongCarFactory.createDaZhong("jieDa");
        jieDa.price();
        DaZhong paSaTe = daZhongCarFactory.createDaZhong("paSaTe");
        paSaTe.price();
        DaZhong sangTaNa = daZhongCarFactory.createDaZhong("sangTaNa");
        sangTaNa.price();

        // 创建保时捷汽车系列工厂
        CarFactory porscheCarFactory = CarFactoryProducer.producePorscheCarFactory();
        // 创建保时捷系列产品
        Porsche porsche718 = porscheCarFactory.createPorsche("porsche718");
        porsche718.price();
        Porsche porsche911 = porscheCarFactory.createPorsche("porsche911");
        porsche911.price();
        Porsche porsche918 = porscheCarFactory.createPorsche("porsche918");
        porsche918.price();
    }
}
