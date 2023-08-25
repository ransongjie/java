package com.xcrj.creation.abstract_factory.example0.carfactory;

import com.xcrj.creation.abstract_factory.example0.dazhong.DaZhong;
import com.xcrj.creation.abstract_factory.example0.porsche.Porsche;

public abstract class CarFactory {
    public abstract DaZhong createDaZhong(String daZhongCarType);

    public abstract Porsche createPorsche(String porscheCarType);
}
