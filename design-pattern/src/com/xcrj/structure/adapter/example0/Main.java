package com.xcrj.structure.adapter.example0;

import com.xcrj.structure.adapter.example0.china.ChinaCharger;

/**
 * 中国充电器，适配器，欧洲充电器
 */
public class Main {
    public static void main(String[] args) {
        // 中国插座，中国充电器插上中国插座就可以充电
        ChinaCharger chinaCharger = new ChinaCharger();
        chinaCharger.charge();

        // 欧标插座，中国充电头需要先插上转接头，再由转接头插上欧标插座就可以充电
        ChinaCharger chinaChargerTravelEU = new ChinaCharger();
        chinaChargerTravelEU.chargeEUJack();
    }
}
