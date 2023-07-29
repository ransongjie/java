package com.xcrj.offer_special.pass1;

import java.util.Arrays;

/**
 * 剑指 Offer II 071. 按权重生成随机数
 * - 给定一个正整数数组w ，其中w[i]代表下标i的权重（下标从0开始），请写一个函数pickIndex，它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
 * - 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 * - 也就是说，选取下标 i的概率为 w[i] / sum(w) 。
 * <p>
 * 已知
 * - 输入为w[]，权重下标 和 对应的权重值
 * - p(下标i被选取的概率)=w[i]/sum(w)
 */
public class Solution71 {

    class Solution {
        int len;
        int total;
        int[] preWSum;

        /**
         * 求w前缀和
         */
        public Solution(int[] w) {
            this.len = w.length;
            this.total = Arrays.stream(w).sum();
            this.preWSum = new int[w.length];
            // 第1个元素的前缀和
            this.preWSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                this.preWSum[i] = preWSum[i - 1] + w[i];
            }
        }

        /**
         * 随机的选取下标i
         * w[i]越大下标i被选取的概率越大
         */
        public int pickIndex() {
            // 向上取整，随机取total的一部分，+1向上取整，为了可能取到 len-1下标
            int x = (int) (Math.random() * this.total) + 1;
            return binarySearch(x);
        }

        /**
         * 二分查找仅小于x（total一部分）的前缀和的下标
         *
         * @param x total的一部分
         */
        private int binarySearch(int x) {
            int l = 0, r = this.len - 1;
            while (l < r) {
                int mid = ((r - l) >> 1) + l;
                // 往右靠拢试着找更大的前缀和
                if (this.preWSum[mid] < x) {
                    l = mid + 1;
                }
                // 往左靠拢试着找稍小的前缀和
                else {
                    r = mid;
                }
            }
            return l;
        }
    }
}
