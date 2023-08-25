package com.xcrj.creation.builder.example0.shuini;

import com.xcrj.creation.builder.example0.FangAn;
import com.xcrj.creation.builder.example0.yunshu.WLHongGuang;
import com.xcrj.creation.builder.example0.yunshu.YunShu;

/*水泥*/
public abstract class ShuiNi implements FangAn {
    public abstract String name();

    public abstract float price();

    public YunShu yunShu() {
        return new WLHongGuang();
    }
}
