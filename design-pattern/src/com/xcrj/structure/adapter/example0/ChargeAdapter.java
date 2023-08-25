package com.xcrj.structure.adapter.example0;

import com.xcrj.structure.adapter.example0.europe.EUCharger;

public class ChargeAdapter {
    private EUCharger euCharger;

    public ChargeAdapter() {
        System.out.println("插入转接头");
        euCharger = new EUCharger();
    }

    /*插入欧洲插座充电*/
    public void chargeEUJack() {
        euCharger.charge();
    }
}
