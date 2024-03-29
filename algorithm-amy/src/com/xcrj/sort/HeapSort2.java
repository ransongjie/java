package com.xcrj.sort;

public class HeapSort2 implements MySort {
    public static void main(String[] args) {
        MySort mySort = new HeapSort2();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        heapSort(as);
    }


    public static void heapSort(int[] as) {
        //初始逆序建立大根堆
        for (int i = as.length / 2; i > -1; i--) {
            heapfy(as, i, as.length);
        }
        //只有堆顶元素无序，重新建立大根堆
        int heapSize = as.length;
        //将每次将as[0]元素放到heapSize
        for (int i = 0; i < as.length; i++) {
            CPer.swap(as, 0, --heapSize);
            heapfy(as, 0, heapSize);
        }
    }

    //仅有堆顶元素无序，重建堆
    //子子比较，父子比较
    public static void heapfy(int[] as, int i, int heapSize) {
        int j = (2 * i) + 1;
        while (j < heapSize) {
            int g = (j + 1) < heapSize ? (as[j] > as[j + 1] ? j : j + 1) : j;
            if (as[i] > as[g]) return;
            CPer.swap(as, i, g);
            i = g;
            j = (2 * i) + 1;
        }
    }
}
