package com.xcrj.creation.builder.example0;

import java.util.ArrayList;
import java.util.List;

/**
 * 房子
 */
public class FangZi {
    private List<FangAn> fangAnList = new ArrayList<FangAn>();

    public void addFangAn(FangAn fangAn) {
        fangAnList.add(fangAn);
    }

    public void showFangAn() {
        for (FangAn fangAn : fangAnList) {
            System.out.print("材料 : " + fangAn.name());
            System.out.print(", 价格 : " + fangAn.price());
            System.out.println(", 运输 : " + fangAn.yunShu().yunShu());
        }
    }

    public void showCost() {
        float cost = 0.0f;
        for (FangAn fangAn : fangAnList) {
            cost += fangAn.price();
        }

        System.out.println("成本：" + cost);
    }
}
