package com.xcrj.basic.pass2.sort;

import java.util.Arrays;

/**
 * 计数排序 统计每个年龄的员工数量
 */
public class CountSort {
    public static void main(String[] args) {
        int[]as={20,45,13};
        int[] cnts=countSort(as);
        for (int i = 0; i < cnts.length; i++) {
            if(cnts[i]!=0){
                System.out.println(i+": "+cnts[i]);
            }
        }
    }

    public static int[] countSort(int[]as) {
        int max=Arrays.stream(as).max().getAsInt();
        int[] cnts=new int[max+1];
        for (int a : as) {
            cnts[a]++;
        }
        return cnts;
    }
}
