package com.xcrj.basic.pass4.sort;

public class QuickSort implements MySort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        CPer.test1(quickSort);
        CPer.test2(quickSort);
    }

    @Override
    public void sort(int[] as) {
        quickSort(as);
    }

    private static void quickSort(int[] as) {
        f(as, 0, as.length - 1);
    }

    // 每趟定下一个轴值
    private static void f(int[] as, int s, int e) {
//        while (s < e) {
        if (s < e) {
            // 递归往下每次定一个轴值
            int m = pivot(as, s, e);
            f(as, s, m - 1);
            f(as, m + 1, e);
        }
    }

    // 都与as[e]比较，放到<=as[e]左边界中
    private static int pivot(int[] as, int s, int e) {
        int l = s - 1;//<as[e]的边界
        while (s < e) {
            if (as[s] <= as[e]) {
                CPer.swap(as, ++l, s);
            }
            s++;
        }
        CPer.swap(as, ++l, e);
        return l;
    }
}
