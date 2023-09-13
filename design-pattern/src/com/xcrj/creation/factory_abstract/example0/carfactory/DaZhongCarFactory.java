package com.xcrj.creation.factory_abstract.example0.carfactory;

import com.xcrj.creation.factory_abstract.example0.dazhong.DaZhong;
import com.xcrj.creation.factory_abstract.example0.dazhong.JieDa;
import com.xcrj.creation.factory_abstract.example0.dazhong.PaSaTe;
import com.xcrj.creation.factory_abstract.example0.dazhong.SangTaNa;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche;

/*
 * 大众系列产品生产工厂
 * */
public class DaZhongCarFactory extends CarFactory {
    @Override
    public Porsche createPorsche(String porscheCarType) {
        return null;
    }

    @Override
    public DaZhong createDaZhong(String daZhongCarType) {
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
}
