package com.xcrj.sort;

public class InsertSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new InsertSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        insertSort(as);
    }

    public static void insertSort(int[] as) {
        for (int i = 1; i < as.length; i++) {
            int temp = as[i], j = i - 1;
            //比我大就后移一位，否则break
            for (; j > -1 && as[j] > temp; j--) {
                as[j + 1] = as[j];//直接赋值
            }
            as[j + 1] = temp;
        }
    }
}
