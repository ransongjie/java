package com.xcrj.structure.adapter.example0.china;

import com.xcrj.structure.adapter.example0.ChargeAdapter;

/**
 * 中国充电头
 */
public class ChinaCharger {
    private ChargeAdapter chargeAdapter;

    /*插入中国插座充电*/
    public void charge() {
        System.out.println("插入中国插座充电");
    }

    /*插入欧洲插座充电*/
    public void chargeEUJack() {
        chargeAdapter = new ChargeAdapter();
        chargeAdapter.chargeEUJack();
    }
}
