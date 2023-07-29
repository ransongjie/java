package com.xcrj.company.pass1;

import java.util.HashMap;

/**
 * 含有k个不同整数的子数组的个数
 */
public class A069SubArrContainsKKind {
    /**
     * 局部最优选择可以获得全局最优解
     *
     * @param as
     * @param k
     * @return
     */
    public static int subArrContainsKKind(int[] as, int k) {
        //<=k种的子数组个数-<=k-1种的子数组个数= 等于k种的子数组个数
        return mostKSubArrNum(as, k) - mostKSubArrNum(as, k - 1);
    }

    private static int mostKSubArrNum(int[] as, int k) {
        int ans = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        int r = 0, l = 0;
        for (; r < as.length; r++) {
            //是否新种类的数字
            if (!count.containsKey(as[r])) {
                k--;
            }
            //添加数字，不管是否新种类
            count.put(as[r], count.getOrDefault(as[r], 0) + 1);
            //k in [0,k]
            while (k < 0) {
                count.put(as[l], count.get(as[l]) - 1);//数量减少
                if (count.get(as[l]) == 0) k++;
                l++;
            }

            //必须以r结尾的数字<=k的子数组个数
            //例如，1 2 3，必须以3结尾，种数<=k的子数组有 3；2 3；1 2 3，共3个满足要求的子数组
            ans += r - l + 1;
        }
        return ans;
    }

    /**
     * 双滑动窗口 =k-1滑动窗口，=k滑动窗口
     * 题目规定，nums中的数字，不会超过nums的长度
     * 两个窗口同时右移直到满足两个窗口一个有k-1种，一个有k种，计算一次
     * 种数增多左边界left需要左移动
     * @param as
     * @param k
     * @return
     */
    public static int subArrContainsKKind2(int[] as, int k) {
        int n = as.length;
        int[] kcnts = new int[n + 1];
        int kleft = 0, kkind = 0;
        int[] k_1cnts = new int[n + 1];
        int k_1left = 0, k_1kind = 0;
        int ans = 0;
        for (int r = 0; r < n; r++) {
            //两个窗口同时右移
            if (kcnts[as[r]] == 0) {
                kkind++;
            }
            if (k_1cnts[as[r]] == 0) {
                k_1kind++;
            }
            //统计次数
            kcnts[as[r]]++;
            k_1cnts[as[r]]++;
            //两个窗口的数字种数过多
            while (k_1kind==k){
                if(k_1cnts[as[k_1left]]==1){
                    k_1kind--;
                }
                k_1cnts[as[k_1left]]--;
                k_1left++;
            }
            while(kkind>k){
                if(kcnts[as[kleft]]==1){
                    kkind--;
                }
                kcnts[as[kleft]]--;
                kleft++;
            }
            //
            ans = k_1left - kleft;
        }

        return ans;
    }
}
