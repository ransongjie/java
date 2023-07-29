package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 060. 出现频率最高的 k 个数字
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 */
public class Solution60 {
    /**
     * 特点：优先队列从小到大排序次数，队列头部次数大于k，则队列中所有次数大于k
     * <p>
     * 先使用hash表统计每个值出现的次数
     * 再使用优先队列放入出现次数更大的元素
     * - 优先队列中有k个元素了，放入出现次数更大的元素：对头次数小于当前元素出现次数，出队对头元素
     * - 队列不满k个元素，直接添加到队列中
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 统计每个元素出现次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 优先队列按照次数从小到大排序
        Queue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int v = entry.getKey();
            int c = entry.getValue();
            // 优先队列中有k个元素了
            if (queue.size() == k) {
                // 放入出现次数更大的元素：对头次数小于当前元素出现次数，出队对头元素
                if (queue.peek()[1] < c) {
                    queue.poll();
                    queue.offer(new int[]{v, c});
                }
            }
            // 队列不满k个元素，直接添加到队列中
            else {
                queue.offer(new int[]{v, c});
            }
        }

        // 将队列中的值转储到数组
        // k：出现频率最高的k个数字
        int[] rs = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            rs[i++] = queue.poll()[0];
        }

        return rs;
    }

    /**
     * 快速排序思想
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // 统计每个元素出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 构建快速排序所需链表
        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }

        // 快速排序
        int[] rs = new int[k];
        quickSort(list, 0, list.size() - 1, k, rs, 0);
        return rs;
    }

    /**
     * 快速排序
     * - 思想：轴值右边的值都小于轴值，轴值左边的值都大于轴值
     * - ！思想：经过随机轴值一次快排得到前m大的值，再判断m与k的关系
     * - 随机轴值一次快排，找到前m大的值，m=pivotJ-start+1，再根据m和k的关系决定是否继续快排
     * - 一次快排之后，[start,j]的值是序列中前j-start+1多的数
     * -- k<=j-start，在[start,j-1]的子序列中继续找前k多的数
     * -- k>j-start=m，找到了前m多的数，继续在[j+1,end]的子序列中找前k-m多的数
     *
     * @param list  待快速排序链表
     * @param start 序列左端点
     * @param end   序列右端点
     * @param k     k
     * @param rs    返回值
     * @param ri    rs[ri]
     */
    public void quickSort(List<int[]> list, int start, int end, int k, int[] rs, int ri) {
        /**
         * [start,j]的值是整个序列中最大的j-start+1个值
         * 快速排序把大于随机轴值的值放到前面
         * [start,j]的值都大于等于j的值
         */
        // 从序列中随机选择一个索引点的中 放到开头做轴值
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        // 交换索引指向元素，索引值没变，start和picked没变
        Collections.swap(list, picked, start);

        // 寻找比轴值大或等的值依次放到轴后面
        int pivot = list.get(start)[1];
        int j = start;
        // =end，end=list.size() - 1
        for (int i = start + 1; i <= end; i++) {
            // 始终和开始值比较
            if (list.get(i)[1] >= pivot) {
                Collections.swap(list, j + 1, i);
                j++;
            }
        }

        // start指向pivot<=[start,j)的值，把小的值放后面
        Collections.swap(list, start, j);

        // [start,j]多于k个元素，快速排序[start,j-1]的序列
        if (k <= j - start) {
            quickSort(list, start, j - 1, k, rs, ri);
        }
        // [start,j]少于k个元素
        else {
            // 找到了前m多的元素，m<k，m=j-start+1
            for (int i = start; i <= j; i++) {
                rs[ri++] = list.get(i)[0];
            }
            // 在[j+1,end]的序列中寻找剩下的k-m多的值
            if (k > j - start + 1) {
                quickSort(list, j + 1, end, k - (j - start + 1), rs, ri);
            }
        }
    }
}
