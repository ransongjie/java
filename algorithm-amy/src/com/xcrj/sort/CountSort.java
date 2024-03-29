package com.xcrj.sort;

import java.util.Arrays;

public class CountSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new CountSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        countSort(as);
    }

    //利用数组下标的有序性
    public static void countSort(int[] as) {
        int max = Arrays.stream(as).max().getAsInt();
        int[] cnts = new int[max + 1];
        for (int a : as) {
            cnts[a]++;//相同数字出现次数
        }
        int k = 0;
        for (int i = 0; i < cnts.length; i++) {
            if (cnts[i] != 0) {
                //同一个数字可能出现多次
                for (int j = 0; j < cnts[i]; j++) {
                    as[k++] = i;
                }
            }
        }
    }
}
