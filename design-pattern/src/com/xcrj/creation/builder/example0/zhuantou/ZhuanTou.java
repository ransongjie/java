package com.xcrj.creation.builder.example0.zhuantou;

import com.xcrj.creation.builder.example0.FangAn;
import com.xcrj.creation.builder.example0.yunshu.DFTianLong;
import com.xcrj.creation.builder.example0.yunshu.YunShu;

/*砖头*/
public abstract class ZhuanTou implements FangAn {
    public abstract String name();

    public abstract float price();

    public YunShu yunShu() {
        return new DFTianLong();
    }
}
