package com.xcrj.creation.factory_simple.example0;

import com.xcrj.creation.factory_simple.example0.dazhong.DaZhong;
import com.xcrj.creation.factory_simple.example0.dazhong.DaZhongCarFactory;

/**
 * 大众汽车工厂，捷达汽车，桑塔纳汽车，帕萨特汽车
 */
public class Main {
    public static void main(String[] args) {
        // 生产捷达汽车
        DaZhong jieDa = DaZhongCarFactory.produceJieDa();
        jieDa.price();

        // 生产帕萨特汽车
        DaZhong paSaTe = DaZhongCarFactory.producePaSaTe();
        paSaTe.price();

        // 生产桑塔纳汽车
        DaZhong sangTaNa = DaZhongCarFactory.produceSangTaNa();
        sangTaNa.price();

        // 创建家用轿车工厂
        DaZhongCarFactory familyCarFactory = new DaZhongCarFactory();

//        // 生产捷达汽车
//        DaZhong jieDa = familyCarFactory.createFamilyCar("jieDa");
//        jieDa.price();
//
//        // 生产帕萨特汽车
//        DaZhong paSaTe = familyCarFactory.createFamilyCar("paSaTe");
//        paSaTe.price();
//
//        // 生产桑塔纳汽车
//        DaZhong sangTaNa = familyCarFactory.createFamilyCar("sangTaNa");
//        sangTaNa.price();
    }
}
