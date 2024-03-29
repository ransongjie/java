package com.xcrj.sort;

public class RadixSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new RadixSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        radixSort(as);
    }

    /**
     * 1. 求所有数的最大位
     * 2. LSD(最次位优先) 遍历每个数的每一位放到cnts（桶）中
     */
    public static void radixSort(int[] as) {
        int maxNum = getMaxDigitNum(as);

        for (int i = 0; i < maxNum; i++) {
            bucketSort(as, i);
        }
    }

    //获取数组中数字的最大位数
    private static int getMaxDigitNum(int[] as) {
        int maxNum = 0;
        for (int a : as) {
            maxNum = Math.max(maxNum, getDigitNum(a));
        }
        return maxNum;
    }

    //获取数字的位数
    private static int getDigitNum(int a) {
        int num = 0;
        while (a != 0) {
            num++;
            a /= 10;
        }
        return num;
    }

    //获取数字第i位的值
    private static int getIV(int a, int i) {
        //i超过数字a的位数
        int num = getDigitNum(a);
        if (i > num) return 0;

        //获取a第i位的值
        for (int j = 0; j < i; j++) {
            a /= 10;
        }
        return a % 10;
    }

    /**
     * @param as
     * @param i  第i位
     */
    private static void bucketSort(int[] as, int i) {
        //统计 元素第i位等=idx 的元素数量
        int[] cnts = new int[10];
        for (int a : as) {
            int iv = getIV(a, i);
            cnts[iv]++;
        }

        //累加 统计 元素第i位<=idx 的元素数量
        //cnts[i]=v, <=i的元素有v个
        for (int j = 1; j < cnts.length; j++) {
            cnts[j] += cnts[j - 1];
        }

        //!!! 倒序as 根据元素第i位 和cnts 放到help数组的合适位置
        //为什么需要倒序？？
        int[] help = new int[as.length];
        for (int j = as.length - 1; j > -1; j--) {
            int iv = getIV(as[j], i);
            int idx = cnts[iv] - 1;
            help[idx] = as[j];
            cnts[iv]--;
        }

        //help数组 拷贝回原数组as
        System.arraycopy(help, 0, as, 0, as.length);
    }
}
