package com.xcrj.interview150.arr_str;

import java.util.ArrayList;
import java.util.List;

/**
 * N 字形变换
 */
public class Solution12 {
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;//只有一行
        //numRows 行
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        //变大 字符先放第1行，再放第2行，...，放第numRows行
        //变小 字符先放第numRows行，...，放第1行
        int flag = -1;//遇到0或numRows-1变成相反数
        int row = 0;//！到第0行，行变大；到第numRows-1行行变小
        for (char c :
                s.toCharArray()) {
            if (0 == row || numRows - 1 == row) flag = -flag;
            rows.get(row).append(c);
            row += flag;
        }

        //逐行整合
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb :
                rows) {
            res.append(sb);
        }
        return res.toString();
    }
}
