package com.xcrj.creation.factory_abstract.example0.carfactory;

import com.xcrj.creation.factory_abstract.example0.dazhong.DaZhong;
import com.xcrj.creation.factory_abstract.example0.porsche.Porsche;

public abstract class CarFactory {
    public abstract DaZhong createDaZhong(String daZhongCarType);

    public abstract Porsche createPorsche(String porscheCarType);
}
