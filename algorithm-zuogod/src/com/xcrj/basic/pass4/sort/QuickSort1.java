package com.xcrj.basic.pass4.sort;

public class QuickSort1 implements MySort {

    public static void main(String[] args) {
        MySort mySort = new QuickSort1();
        CPer.test1(mySort);
        CPer.test2(mySort);
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
            int[] ms = pivot(as, s, e);
            f(as, s, ms[0]);
            f(as, ms[1], e);
        }
    }

    // 以as[e]为轴值 定小于等于和大于的边界
    private static int[] pivot(int[] as, int s, int e) {
        int l = s - 1;
        int r = e + 1;
        int t = as[e];
        while (s < r) {
            if (as[s] < t) {
                CPer.swap(as, ++l, s);
            } else if (as[s] > t) {
                CPer.swap(as, --r, s);
                s--;
            }
            s++;
        }
        as[--r] = t;
        return new int[]{l, r + 1};
    }
}
