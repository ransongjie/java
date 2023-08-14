package com.xcrj.company.pass1;

/**
 * 递增子序列的最大长度
 * - 递增且最大
 * - 数字可以不连续，可以不+1
 * <p>
 * 求整个数组的最大递增子序列
 * - 求以元素元素结尾的最大最大递增子序列》求max
 * - 求整个数组的每个子序列的最大递增子序列》求max
 * <p>
 * 求以i结尾的最大递增子序列
 * - dp[i]
 * - 遍历0~i-1元素，求比as[i]小的数有几个
 * <p>
 * 求最大递增子序列长度是i的元素值
 * - end[i]
 * - 最大递增子序列长度=2，依赖于最大递增子序列长度=1
 * - 最大递增子序列长度=1时，下一个元素值是不是比end[]已知区域所有值都大，扩充到end[i+1]
 * - 最大递增子序列长度=1时，下一个元素值在end[]已知区域中找不到比我大的，扩充到end[i+1]
 * <p>
 *     https://zhuanlan.zhihu.com/p/78224512
 * end[i]
 * - 所有长度为i+1的子序列中，min结尾元素值=end[i]
 * - 遍历到i+1，二分在end[0~i]找，
 * -- 找到了刚好>=as[i+1]的元素，
 * 证明长度为i+1的已知子序列的最小结尾元素值end[l]还>=as[i+1]，"as[i+1]"无法扩充，as[i+1]替换end[l]
 * -- 没找到刚好>=as[i+1]的元素，
 * 证明长度为i+1的已知子序列的最小结尾元素值<as[i+1]，"as[i+1]"可以扩充，end[i+1]=as[i+1]
 */
public class A017MaxIncSub {
    public static int maxIncSub(int[] as) {
        int[] end = new int[as.length];
        end[0] = as[0];
        int max = 1;
        int l = 0, r = 0, m = 0, right = 0;
        for (int i = 1; i < as.length; i++) {
            l = 0;//end[]已经有值区间左边界
            r = right;//右边界
            while (l <= r) {//在end[]已知区域中求>=as[i]的最小值
                m = (l + r) >> 1;
                if (as[i] > end[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            end[l] = as[i];//l经过二分后 记录了 >=as[i]的最小值 或 右边界+1
            right = Math.max(right, l);//right记录了右边界
            max = Math.max(max, right + 1);
            //max=Math.max(max,l+1);
        }
        return max;
    }

}
