package com.xcrj.creation.builder.example0;

import com.xcrj.creation.builder.example0.shuini.FuHeShuiNi;
import com.xcrj.creation.builder.example0.shuini.KZhaShuiNi;
import com.xcrj.creation.builder.example0.zhuantou.HuoZhuan;
import com.xcrj.creation.builder.example0.zhuantou.ShuiNiZhuan;

public class FangZiBuilder {
    /*公寓*/
    public FangZi buildGongYu() {
        FangZi gongYu = new FangZi();
        gongYu.addFangAn(new KZhaShuiNi());
        gongYu.addFangAn(new ShuiNiZhuan());
        return gongYu;
    }

    /*别墅*/
    public FangZi buildBieShu() {
        FangZi bieShu = new FangZi();
        bieShu.addFangAn(new FuHeShuiNi());
        bieShu.addFangAn(new HuoZhuan());
        return bieShu;
    }
}
