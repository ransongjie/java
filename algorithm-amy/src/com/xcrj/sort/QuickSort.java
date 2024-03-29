package com.xcrj.sort;

public class QuickSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new QuickSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        quickSort(as);
    }

    public static void quickSort(int[] as) {
        f(as, 0, as.length - 1);
    }

    private static void f(int[] as, int s, int e) {
        if (s < e) {
            int pivot = pivot(as, s, e);
            f(as, s, pivot - 1);
            f(as, pivot + 1, e);
        }
    }

    //一趟排序和as[e]做比较，<=as[e]放到其左边，确定as[e]的位置
    private static int pivot(int[] as, int s, int e) {
        int l = s - 1;//<=as[r]的边界
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
