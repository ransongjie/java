package com.xcrj.sort;

public class SelectSort implements MySort{
    public static void main(String[] args) {
        MySort mySort=new SelectSort();
        CPer.test1(mySort);
        CPer.test2(mySort);
    }

    @Override
    public void sort(int[] as) {
        selectSort(as);
    }

    //一趟排序确定左侧的1个位置
    public void selectSort(int[]as){
        int idx=0;
        for (int i = 0; i < as.length; i++) {
            idx=i;//记录小值的坐标
            for (int j = i+1; j < as.length; j++) {
                if(as[idx]>as[j]){
                    idx=j;
                }
            }
            CPer.swap(as,idx,i);
        }
    }
}
