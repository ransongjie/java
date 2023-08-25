package com.xcrj.creation.builder.example0;

/**
 * 建造者，房子，方案，公寓，别墅，水泥，砖头，运输
 */
public class Main {
    public static void main(String[] args) {
        FangZiBuilder builder = new FangZiBuilder();

        // 建造公寓
        FangZi gongYu = builder.buildGongYu();
        gongYu.showFangAn();
        gongYu.showCost();

        // 建造别墅
        FangZi bieShu = builder.buildBieShu();
        bieShu.showFangAn();
        bieShu.showCost();
    }
}
