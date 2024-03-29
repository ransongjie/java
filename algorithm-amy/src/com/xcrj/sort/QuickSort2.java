package com.xcrj.sort;

public class QuickSort2 implements MySort {
    public static void main(String[] args) {
        MySort mySort = new QuickSort2();
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

    private static void f(int[] as, int s, int e) {
        if (s < e) {
            int[] lr = pivot(as, s, e);
            f(as, s, lr[0]);
            f(as, lr[1], e);
        }
    }

    //一趟和as[e]比较，<as[e]放其左边，>as[e]放其右边，自然=as[e]在中间
    //返回左右边界
    private static int[] pivot(int[] as, int s, int e) {
        int rand = (int) (s + ((e - s) * Math.random()));
        CPer.swap(as, rand, e);
        int l = s - 1, r = e + 1, t = as[e];
        while (s < r) {//<r
            if (as[s] < t) {
                CPer.swap(as, ++l, s);
            } else if (as[s] > t) {
                CPer.swap(as, --r, s);
                s--;//交换过来的值并未被比较
            }
            s++;
        }
        as[r - 1] = t;//
        return new int[]{l, r};
    }
}
