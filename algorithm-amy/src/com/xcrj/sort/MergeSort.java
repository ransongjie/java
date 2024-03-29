package com.xcrj.sort;

public class MergeSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new MergeSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        mergeSort(as);
    }

    public static void mergeSort(int[] as) {
        f(as, 0, as.length - 1);
    }

    //递归控制子序列个数和长度，再merge
    private static void f(int[] as, int s, int e) {
        if (s < e) {
            int m = s + (e - s) / 2;
            f(as, s, m);
            f(as, m + 1, e);
            merge(as,s,m,e);//
        }
    }

    private static void merge(int[] as, int s, int m, int e) {
        int i = s, j = m + 1, k = 0;
        int[] bs = new int[e - s + 1];
        while (i <= m && j <= e) {
            if (as[i] <= as[j]) {
                bs[k++] = as[i++];
            } else {
                bs[k++] = as[j++];
            }
        }

        while (i <= m) {
            bs[k++] = as[i++];
        }
        while (j <= e) {
            bs[k++] = as[j++];
        }

        System.arraycopy(bs, 0, as, s, bs.length);
    }
}
