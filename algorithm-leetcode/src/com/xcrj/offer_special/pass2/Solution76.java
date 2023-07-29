package com.xcrj.offer_special.pass2;

import java.util.Random;

/**
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 */
public class Solution76 {

    /**
     * 已知
     * - 轴值左分区数值< 轴值 <轴值右分区数值
     * 
     * 快速选择排序
     * - 随机轴：使用随机数定一个轴
     * - 确定轴位置：
     * -- 交换轴和start的值，
     * -- 遍历（从start+1开始）整个序列将小于等于轴值的值交换到j（从start开始）
     * -- 交换start和j+1
     * - 选择左或右分区继续排序：
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        // 快速排序是升序，第k大转换为第len-k小
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }

    Random random=new Random();

    public int quickSelect(int[] nums, int s, int e, int rk) {
        int pivot=randomPartition(nums, s, e);
        if(pivot==rk)return nums[rk];
        else{
            return pivot>rk?quickSelect(nums, s, pivot-1, rk):quickSelect(nums, pivot+1, e, rk);
        }
    }

    private int randomPartition(int[] nums, int s, int e) {
        // e-s+1为元素个数
        int pivot=random.nextInt(e-s+1)+s;
        swap(nums, s, pivot);
        return partition(nums, s, e);
    }

    public int partition(int[] nums, int s, int e) {
        /**
         * xcrj
         * s,[1,j]
         * pivotValue,[小于pivotValue的数]
         * 最后将 s和j的数值交换
         */
        int pivotValue=nums[s];
        int j=s;
        // xcrj =e，e需要参与比较
        for(int i=s+1;i<=e;i++){
            if(nums[i]<=pivotValue) swap(nums, ++j, i);
        }
        swap(nums, s, j);
        return j;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 堆排序
     * - 找第k大的数，重建堆进行k次即可
     * <p>
     * 介绍：
     * - 初始建堆（逆筛到根结点），重建堆（不断将堆顶元素放到数组后面）
     * 
     * - 大根堆：堆顶元素r[0]是这个数组的最大值
     * - 简单选择排序每一趟排序只让1个元素有序，剩余元素依旧无序；堆排序每一趟排序都让整个序列更加有序
     * - 快速排序对原始序列的状态敏感，堆不敏感，这是堆对比相较于快速排序的唯一优点
     * 特点：大根堆-父节点大于左右孩子结点
     */
    public int findKthLargest2(int[] nums, int k) {
        return heapSort(nums, k);
    }

    public int heapSort(int[] r, int k) {
        int n=r.length;
        // 1. 初始建堆，逆筛到根结点; 从第n/2个结点开始建堆，第n/2个结点是第1个有孩子的结点
        for(int i=n/2;i>=0;i--){
            sift(r, i, n);
        }

        // 2. 重建堆，将堆顶元素放到无序数组尾部，只有堆顶无序重建堆即可
        int last;
        for(int i=0;i<k-1;i++){
            last=n-i-1;
            swap(r, last, 0);
            sift(r, 0, last);
        }

        return r[0];
    }

    /**
     * 构建堆
     *
     * @param r 输入数组
     * @param i 从第i个记录建堆
     * @param n 待建堆元素个数 
     */
    public void sift(int[] r, int i, int n) {
        // 子节点
        int j=2*i;
        while(j+1<=n){
            // 比较子节点
            // xcrj j+1=n时，j指向最后1个元素，不进行子节点比较
            if(j+1<n&&r[j+1]>r[j])j+=1;
            // 比较父子结点。大根堆，父>=子，退出
            // xcrj >= 就break
            if(r[i]>=r[j]) break;
            // 大根堆，父<子，继续往下
            else{
                swap(r, i, j);
                i=j;
                j*=2;
            }
        }
    }
}
