package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 060. 出现频率最高的 k 个数字
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 */
public class Solution60 {
    /**
     * map<数字,次数>
     * priorityQue(从小到大)+限制容量为k
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        Queue<int[]> que=new PriorityQueue<>((s1,s2)->s1[1]-s2[1]);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            int num=entry.getKey();
            int times=entry.getValue();

            // 将出现频率最高的k个数字放入优先队列
            if(que.size()==k){
                // xcrj 对头<小于<队尾 对头到队尾越来越大 小的在前面
                if(que.peek()[1]<times){
                    que.poll();
                    que.offer(new int[]{num,times});
                }
            }else{
                que.offer(new int[]{num,times});
            }
        }

        int[]rs=new int[k];
        for(int i=0;i<k;i++){
            rs[i]=que.poll()[0];
        }

        return rs;
    }

    /**
     * 随机快速排序
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        List<int[]> list=new ArrayList<>();
        map.forEach((num,times)->list.add(new int[]{num,times}));

        int[]rs=new int[k];
        quickSort(list, 0, list.size()-1, k, rs, 0);

        return rs;
    }

    /**
     * 快速排序
     * - 思想：轴值右边的值都小于轴值，轴值左边的值都大于轴值
     * 
     * xcrj 思想：经过随机轴值一次快排得到前m大的值，再判断m与k的关系
     * - 随机轴值一次快排，找到前m大的值，m=pivotJ-start+1，再根据m和k的关系决定是否继续快排
     * - 一次快排之后，[start,j]的值是序列中前j-start+1多的数
     * -- k<m，k<j-start+1，在[start,j-1]的子序列中找前k多的数
     * -- k>m，k>j-start+1，先把找到的m个元素放入rs[]中，在[j+1,end]的子序列中找前k-m多的数
     *
     * @param list  待快速排序链表
     * @param start 序列左端点
     * @param end   序列右端点
     * @param k     k
     * @param rs    返回值
     * @param ri    rs[ri]
     */
    public void quickSort(List<int[]> list, int start, int end, int k, int[] rs, int ri) {
        /* 一趟快速排序 */
        // 获取随机轴值索引
        int pivotIdx=(int)(Math.random()*(end-start+1)+start);
        // 交换值，索引不变
        // start指向pivot
        Collections.swap(list, pivotIdx, start);

        // 确定pivot位置，所有大于povit的值放到povit左边
        int j=start;
        // i=start+1, 上面交换之后 start位置就是pivot
        // pivot后面的所有值和pivot比较
        for(int i=start+1;i<=end;i++){
            if(list.get(i)[1]>=list.get(start)[1]){
                // 从j+1开始放置比pivot更大的值
                Collections.swap(list, j+1, i);;
                j++;
            }
        }
        Collections.swap(list, j,start);

        /* 比较k和m */
        // m>=k，在[start,j-1]的子序列中找前k多的数
        if(k<j-start+1) quickSort(list, start, j-1, k, rs, ri);
        // m<k
        else{
            // 找到了前m多的元素，先放入rs中
            for(int i=start;i<=j;i++){
                rs[ri++]=list.get(i)[0];
            }

            // 在[j+1,end]的子序列中找前k-m多的数
            if(k>j-start+1){
                quickSort(list, j + 1, end, k - (j - start + 1), rs, ri);
            }
        }
    }
}
