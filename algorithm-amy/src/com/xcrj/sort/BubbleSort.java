package com.xcrj.sort;

public class BubbleSort implements MySort {
    public static void main(String[] args) {
        MySort mySort = new BubbleSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        bubbleSort(as);
    }

    //一趟排序确定上面的1个位置
    public void bubbleSort(int[] as) {
        for (int i = as.length; i > 0; i--) {//有序边界
            for (int j = 1; j < i; j++) {
                if (as[j - 1] > as[j]) {
                    CPer.swap(as, j - 1, j);
                }
            }
        }
    }
}
