package com.xcrj.creation.factory_abstract.example0.carfactory;

import com.xcrj.creation.factory_abstract.example0.dazhong.DaZhong;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche718;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche911;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche918;

/*
* 保时捷系列产品生产工厂
* */
public class PorscheCarFactory extends CarFactory {

    @Override
    public DaZhong createDaZhong(String daZhongCarType) {
        return null;
    }

    @Override
    public Porsche createPorsche(String porscheCarType) {
        if (porscheCarType == null) {
            return null;
        }

        if (porscheCarType.equalsIgnoreCase("porsche718")) {
            return new Porsche718();
        }

        if (porscheCarType.equalsIgnoreCase("porsche911")) {
            return new Porsche911();
        }

        if (porscheCarType.equalsIgnoreCase("porsche918")) {
            return new Porsche918();
        }

        return null;
    }
}
