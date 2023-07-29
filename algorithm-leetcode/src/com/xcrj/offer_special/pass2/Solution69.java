package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 069. 山峰数组的顶部
 * 已知，arr[]是1个单峰数组
 * 山峰数组的顶部，左侧序列<arr[i]<右侧序列
 */
public class Solution69 {
    public int peakIndexInMountainArray1(int[] arr) {
        // i=1~len-1, 山峰在数组中间
        for(int i=1;i<arr.length-1;i++){
            if(arr[i]>arr[i+1]) return i;
        }
        return -1;
    }

    /**
     * 二分查找寻找最大值索引
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray2(int[] arr) {
        // i=1~len-1, 山峰在数组中间
        int l=1,r=arr.length-1,o=-1;
        while(l<=r){
            int mid=(r+l)>>1;
            // 左边更大，往左边走找最大值
            if(arr[mid]>arr[mid+1]){
                o=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return o;
    }
}
