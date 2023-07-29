package com.xcrj.company.pass1;

import java.util.ArrayList;
import java.util.HashMap;

public class A074Bigger2ArithmeticSequence {
    public static int bigger2ArithmeticSequence(int[]as){
        int ans=0;
        int n=as.length;
        ArrayList<HashMap<Integer,Integer>> maps=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maps.add(new HashMap<>());
            //i逐个和i前面的元素值比较
            for (int j = i-1; j >=0 ; j--) {
                //元素都是int类型，防止越界
                long diff=(long)as[i]-(long)as[j];
                if(diff<Integer.MIN_VALUE||diff>Integer.MAX_VALUE){
                    continue;
                }
                /**
                 * 22 24 24 26
                 * 第1个24，<2, 1>
                 * 第2个24，<2, 1>
                 * 26和第2个24，<2,1>+<2,0>+1=<2,2>，以第2个24结尾的子序列添加上26
                 * - <2,1>: 22 24_2, 26
                 * - <2,0>: 26
                 * - 1: 24_2, 26
                 * 长度>2的等差子序列个数=1=第2个24...
                 * 26和第1个24，<2,1>+<2,2>+1=<2,4>，以第1个24结尾的子序列添加上24 26
                 * - <2,1>: 22 24_1, 26
                 * - <2,2>: 22 24_2, 26; 24_2, 26
                 * - 1: 24_1, 26
                 * 长度>2的等差子序列个数=1=第1个24...
                 */
                int dif=(int)diff;
                int endJNum=maps.get(j).getOrDefault(dif,0);
                //长度>2的等差子序列的个数
                ans+=endJNum;
                //长度>=2的等差子序列的个数
                int endINum=maps.get(i).getOrDefault(dif,0);
                maps.get(i).put(dif,endINum+endJNum+1);
            }
        }
        return ans;
    }
}
