package com.xcrj.creation.factory_simple.example0.dazhong;

/*
 * 家用轿车生产工厂
 * */
public class DaZhongCarFactory {
    public DaZhong produceDaZhongCar(String daZhongCarType) {
        if (daZhongCarType == null) {
            return null;
        }

        if (daZhongCarType.equalsIgnoreCase("jieDa")) {
            return new JieDa();
        }

        if (daZhongCarType.equalsIgnoreCase("paSaTe")) {
            return new PaSaTe();
        }

        if (daZhongCarType.equalsIgnoreCase("sangTaNa")) {
            return new SangTaNa();
        }

        return null;
    }

    public static DaZhong produceJieDa() {
        return new JieDa();
    }

    public static DaZhong producePaSaTe() {
        return new PaSaTe();
    }

    public static DaZhong produceSangTaNa() {
        return new SangTaNa();
    }
}
