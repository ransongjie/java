package com.xcrj.sort;

public class ShellSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new ShellSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        shellSort(as);
    }

    //最外圈for用于步长控制
    public static void shellSort(int[] as) {
        for (int s = as.length / 2; s >= 1; s /= 2) {
            for (int i = s; i < as.length; i++) {//i=s
                int tmp = as[i];
                int j = i - s;//
                for (; j > -1 && as[j] > tmp; j -= s) {
                    as[j + s] = as[j];
                }
                as[j + s] = tmp;
            }
        }
    }
}
