package com.xcrj.offer_special.pass1;

import java.util.Random;

/**
 * 剑指 Offer II 076. 数组中的第 k 大的数字
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 提示：
 * - 1 <= k <= nums.length <= 104
 * - -104 <= nums[i] <= 104
 */
public class Solution76 {
    /**
     * 已知
     * - 轴值左分区数值< 轴值 <轴值右分区数值
     * <p>
     * 快速选择排序
     * - 随机分区：随机数轴值进行随机分区。随机轴值，以nums[随机数i]为轴值分区
     * - 轴确定：扩充小于轴值序列进行轴确定。将序列中<=随机轴值的值，放到随机轴值的左边。使用j记录左侧子序列的右边界，扩充小于序列确定轴
     * - 选择左或右分区进行排序
     * <p>
     * 快速排序：
     * - 固定分区，start~end
     * - 轴确定：左右比较交换确定轴值
     * - 左右分区都要进行排序
     */
    public int findKthLargest1(int[] nums, int k) {
        // nums.length - k，序列中第k大的元素，降序第k个元素，升序第nums.length-k个元素
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    Random random = new Random();

    /**
     * @param nums
     * @param s    分区开始
     * @param e    分区结束
     * @param rk   正序第rk个小的值
     */
    public int quickSelect(int[] nums, int s, int e, int rk) {
        // 随机分区获取轴pivot
        int pivot = randomPartition(nums, s, e);
        if (pivot == rk) {
            return nums[pivot];
        }
        // 快速选择排序，选择分区。
        else {
            return pivot < rk ? quickSelect(nums, pivot + 1, e, rk) : quickSelect(nums, s, pivot - 1, rk);
        }
    }

    private int randomPartition(int[] nums, int s, int e) {
        // ！随机轴值，以nums[随机数i]为轴值分区
        // e-s+1为s到e的元素个数
        int i = random.nextInt(e - s + 1) + s;
        // 交换i指向值和右边界值
        swap(nums, i, e);
        // ！将序列中<=随机轴值的值，放到随机轴值的左边
        return partition(nums, s, e);
    }

    public int partition(int[] nums, int s, int e) {
        int v = nums[e];
        // j记录<=v的分区右边界
        int j = s - 1;
        // 遍历整个序列，扩充<=v的值的分区
        for (int m = s; m < e; m++) {
            if (nums[m] <= v) {
                swap(nums, ++j, m);
            }
        }
        // nums[0~j]<=nums[随机数i]<nums[j~e]
        swap(nums, j + 1, e);
        return j + 1;
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
     * - 大根堆：堆顶元素r[0]是这个数组的最大值
     * - 简单选择排序每一趟排序只让1个元素有序，剩余元素依旧无序；堆排序每一趟排序都让整个序列更加有序
     * - 快速排序对原始序列的状态敏感，堆不敏感，这是堆对比相较于快速排序的唯一优点
     * 特点：大根堆-父节点大于左右孩子结点
     */
    public int findKthLargest2(int[] nums, int k) {
        return heapSort(nums, k);
    }

    public int heapSort(int[] r, int k) {
        // 数组中实际元素个数
        int n = r.length;
        // 1. 初始建堆，逆筛到根结点; 从第n/2个结点开始建堆，第n/2个结点是第1个有孩子的结点
        for (int i = n / 2; i >= 0; i--) {
            sift(r, i, n);
        }
        // 交换临时变量
        int temp;
        // 堆末尾记录下标
        int last;
        // 2. 重建堆；构建有序数组，堆顶r[0]是根结点（最大值）
        // 找第k大的数，重建堆进行k次即可
        for (int i = 0; i < k - 1; i++) {
            last = n - i - 1;
            // 交换, 将最大值r[0]放到数组末尾
            temp = r[last];
            r[last] = r[0];
            r[0] = temp;
            // 交换重新堆，现在只有堆顶不是大根堆
            sift(r, 0, last);
        }

        return r[0];
    }

    /**
     * 构建堆
     *
     * @param r 输入数组
     * @param i 从第i个记录建堆
     * @param l 堆中最后1个元素的索引
     */
    public void sift(int[] r, int i, int l) {
        // 第i个记录的子结点
        int j = 2 * i;
        // 交换临时变量
        int temp;
        while (j + 1 <= l) {
            // 子结点比较
            // j + 1 < l只有1个子结点，则字结点不进行比较
            if (j + 1 < l && r[j] < r[j + 1]) j++;
            // 父子结点比较，小则继续，大则退出
            if (r[i] >= r[j]) break;
            else {
                // 交换
                temp = r[j];
                r[j] = r[i];
                r[i] = temp;
                // 继续
                i = j;
                j = 2 * i;
            }
        }
    }

    public static void main(String[] args) {
        Solution76 solution76 = new Solution76();
        System.out.println(solution76.findKthLargest2(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 1));
    }
}
