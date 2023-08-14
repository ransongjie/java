package com.xcrj.interview150.dp1;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence
 * 最长递增子序列
 * 子序列 不连续
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 */
public class Solution4 {
    /**
     * 求整个数组的最长递增子序列
     *
     * <p>
     * right+1=end[]有值区间的长度=0~i子串的最长递增子序列的长度
     * <p>
     * end[i]=所有长度为i+1的子序列中，min结尾元素值
     * - 遍历到i+1，二分在end[0~i]找，
     * -- 找到了刚好>=as[i+1]的元素，
     * 证明长度为i+1的已知子序列的最小结尾元素值end[l]还>=as[i+1]，不扩充"as[i+1]"，as[i+1]替换end[l]
     * -- 没找到刚好>=as[i+1]的元素，
     * 证明长度为i+1的已知子序列的最小结尾元素值<as[i+1]，可以扩充"as[i+1]"，end[i+1]=as[i+1]
     *
     * @param as
     * @return
     */
    public int lengthOfLIS(int[] as) {
        int[] end = new int[as.length];
        end[0] = as[0];//end[长度为1]=min结尾元素值=as[0]
        int max = 1;//最长递增子序列的长度至少为1

        int l = 0, r = 0, m = 0, right = 0;
        for (int i = 1; i < as.length; i++) {
            //end[]已经有值区间, 左边界
            l = 0;
            //end[]已经有值区间, 右边界
            r = right;

            //end[]已经有值区间, 求>=as[i]的最小值
            while (l <= r) {
                m = (l + r) >> 1;
                if (as[i] > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            end[l] = as[i];//l经过二分, 记录了>=as[i]的最小值 或 右边界+1
            right = Math.max(right, l);//right记录了end[]有值的右边界
            max = Math.max(max, right + 1);//right+1=end[]有值区间的长度=0~i子串的最长递增子序列的长度
            //max=Math.max(max,l+1);
        }
        return max;
    }
}
