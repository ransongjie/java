package com.xcrj.basic.pass4.sort;

public class HeapSort implements MySort {
    public static void main(String[] args) {
        CPer.test1(new HeapSort2());
        CPer.test2(new HeapSort2());
    }

    @Override
    public void sort(int[] as) {
        heapSort(as);
    }

    //建立大根堆，把堆顶元素放到末尾，升序数组
    private static void heapSort(int[] as) {
        for (int i = 0; i < as.length; i++) {
            heapInsert(as, i);
        }
        int heapSize = as.length;
        for (int i = 0; i < as.length; i++) {
            CPer.swap(as, 0, --heapSize);
            heapfy(as, 0, heapSize);//只有堆顶无序
        }
    }

    //往上，自己和自己的父亲比较
    private static void heapInsert(int[] as, int i) {
        int p = (i - 1) / 2;
        while (p >= 0 && as[i] > as[p]) {
            CPer.swap(as, i, p);
            i = p;
            p = (i - 1) / 2;
        }
    }

    //往下，子子比较，父子比较
    private static void heapfy(int[] as, int i, int heapSize) {
        int j = 2 * i + 1;// 左孩子公式
        while (j < heapSize) {
            int g = j + 1 < heapSize ? (as[j] > as[j + 1] ? j : j + 1) : j;
            if (as[i] > as[g]) return;//已经是大根堆
            CPer.swap(as, i, g);
            i = g;
            j = 2 * i + 1;
        }
    }
}
