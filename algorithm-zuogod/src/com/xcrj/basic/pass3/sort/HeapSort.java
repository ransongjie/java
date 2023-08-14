package com.xcrj.basic.pass3.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] as = {2, 1, 5, 9, 5, 7, 0, 8};
        heapSort(as);
        System.out.println(Arrays.toString(as));
    }

    public static void heapSort(int[] as) {
        int n = as.length;
        for (int i = 0; i < n; i++) {
            heapInsert(as, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(as, 0, i);
            heapfy(as, i);
        }
    }

    /**
     * 从0~len每次都把大的放到前面
     *
     * @param as
     * @param i
     */
    public static void heapInsert(int[] as, int i) {
        //孩子比父亲更大，把孩子放到前面，每个结点都会成为1次孩子
        while (as[i] > as[(i / 2)]) {
            swap(as, i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 从0~heapSize重建堆
     *
     * @param as
     * @param heapSize
     */
    public static void heapfy(int[] as, int heapSize) {
        int i = 0, l = 1, r = 2, g = 0;
        while (l < heapSize) {//l更大 大于 i
            if (r >= heapSize || as[l] > as[r]) g = l;
            else g = r;
            if (as[i] < as[g]) swap(as, i, g);
            else break;
            //继续往下
            i = g;
            l = 2 * i + 1;
            r = 2 * i + 2;
        }
    }

    /**
     * 根据索引交换数组中的两个值
     *
     * @param as
     * @param a
     * @param b
     */
    public static void swap(int[] as, int a, int b) {
        int tmp = as[a];
        as[a] = as[b];
        as[b] = tmp;
    }
}
