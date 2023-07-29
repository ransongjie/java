package com.xcrj.offer_special.pass2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 075. 数组相对排序
 * arr2 中的元素各不相同
 * arr2元素数量<arr1元素数量
 * arr2元素都在arr1中
 * 要求：
 * - arr1, 在arr2中的元素按照arr2中元素顺序
 * - arr1，不在arr2中的元素按照大小升序
 */
public class Solution75 {
    /**
     * 自定义Comparator
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        // hash表记录<值,次序>
        Map<Integer,Integer> arr2EidxMap=new HashMap<>();
        for(int i=0;i<arr2.length;i++){
            arr2EidxMap.put(arr2[i],i);
        }

        // xcrj
        Integer[] arrI1=Arrays.stream(arr1).boxed().toArray(Integer[]::new);
        // xcrj 更换了排序的规则
        Arrays.sort(arrI1,(a,b)->{
            // arr1中的a和b都在arr2中，按照arr2中a和b索引的相同顺序排序
            if(arr2EidxMap.containsKey(a)&&arr2EidxMap.containsKey(b)){
               return arr2EidxMap.get(a)-arr2EidxMap.get(b);
            }
            // arr1中的a和b都不在arr2中，按照arr1中a和b值的大小排序
            if(!arr2EidxMap.containsKey(a)&&!arr2EidxMap.containsKey(b)){
                return a-b;
            }
            // arr1中的a或b在arr2中，无先后顺序 xcrj 两种顺序都考虑，返回0(代表相等)
            if(!arr2EidxMap.containsKey(a)&&arr2EidxMap.containsKey(b)){
                return 0;
            }
            if(arr2EidxMap.containsKey(a)&&!arr2EidxMap.containsKey(b)){
                return 0;
            }
            return 0;
        });

        // xcrj Integer[]》int[]
        return Arrays.stream(arrI1).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 计数排序：arr1中每个元素出现的次数 fs[arr1[i]]=arr1[i]出现次数
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int maxValue=0;
        for(int a1:arr1){
            maxValue=Math.max(maxValue,a1);
        }
        // +1, 0~maxValue
        // xcrj fs[]对arr1中的元素值进行了排序
        int[] fs=new int[maxValue+1];
        for(int a1:arr1){
            fs[a1]++;
        }
        // 处理在arr2中的元素
        int[] rs=new int[arr1.length];
        int idx=0;
        for(int a2:arr2){
            for(int i=0;i<fs[a2];i++){
                rs[idx++]=a2;
            }
            fs[a2]=0;
        }
        // 处理不在arr2中的元素
        for(int a1=0;a1<fs.length;a1++){
            for(int i=0;i<fs[a1];i++){
                rs[idx++]=a1;
            }
        }

        return rs;
    }
}
