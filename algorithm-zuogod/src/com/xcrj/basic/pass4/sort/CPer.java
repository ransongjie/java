package com.xcrj.basic.pass4.sort;

import java.util.Arrays;

public class CPer {
    public static int[] getAs(int maxLen, int maxV) {
        int len = (int) (Math.random() * maxLen);
        int[] as = new int[len];
        for (int i = 0; i < len; i++) {
            int v = (int) (Math.random() * maxV);
            as[i] = v;
        }
        return as;
    }

    public static int[] cp(int[] as) {
        Arrays.sort(as);
        return as;
    }

    public static void swap(int[] as, int a, int b) {
        int tmp = as[a];
        as[a] = as[b];
        as[b] = tmp;
    }

    public static void test1(MySort mySort) {
        int[] as = {2, 1, 5, 9, 5, 7, 0, 8};
        mySort.sort(as);
        System.out.println(Arrays.toString(as));
    }

    public static void test2(MySort mySort) {
        int times = 100;
        int maxLen = 10;
        int maxV = 100;
        for (int i = 0; i < times; i++) {
            int[] as = CPer.getAs(maxLen, maxV);
            int[] as0 = Arrays.copyOf(as, as.length);
            int[] as1 = Arrays.copyOf(as, as.length);
            int[] as2 = Arrays.copyOf(as, as.length);

            CPer.cp(as1);
            mySort.sort(as2);
            if (!Arrays.equals(as1, as2)) {
                System.out.println("not good");
                System.out.println(Arrays.toString(as0));
                System.out.println(Arrays.toString(as1));
                System.out.println(Arrays.toString(as2));
                throw new RuntimeException();
            }
        }
    }
}
