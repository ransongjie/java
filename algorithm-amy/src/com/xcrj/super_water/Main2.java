package com.xcrj.super_water;

import java.util.*;

/**
 * 多数元素是指在数组中出现次数 大于 ⌊n/k⌋ 的元素
 */
public class Main2 {
    /**
     * ⌊n/2⌋只有1个水王数
     * ⌊n/k⌋只有k-1个水王数
     *
     * @param as
     * @param k
     */
    public static List<Integer> superWaterK(int[] as, int k) {
        List<Integer> ans = new ArrayList<>();
        //候选者
        Map<Integer, Integer> candCnt = new HashMap<>();
        for (int a : as) {
            if (candCnt.containsKey(a)) {
                candCnt.put(a, candCnt.get(a) + 1);
            } else {
                if (candCnt.size() == k - 1) {
                    minus1(candCnt);
                } else {
                    candCnt.put(a, 1);
                }
            }
        }
        //没有水王数
        if (candCnt.size() == 0) return ans;
        //统计每个候选者
        for (Map.Entry<Integer, Integer> e : candCnt.entrySet()) {
            e.setValue(0);
        }
        for (int a : as) {
            if (candCnt.containsKey(a)) {
                candCnt.put(a, candCnt.get(a) + 1);
            }
        }
        //k-1个水王数
        for (Map.Entry<Integer, Integer> e : candCnt.entrySet()) {
            if (e.getValue() > as.length / k) {
                ans.add(e.getKey());
            }
        }
        return ans;
    }

    //value-1, remove candCnt -1后=0的entry
    private static void minus1(Map<Integer, Integer> candCnt) {
        Set<Integer> rmKey = new HashSet<>();
        for (Map.Entry<Integer, Integer> e : candCnt.entrySet()) {
            if (e.getValue() - 1 == 0) {
                rmKey.add(e.getKey());
            } else {
                e.setValue(e.getValue() - 1);
            }
        }

        for (Integer k : rmKey) {
            candCnt.remove(k);
        }
    }
}
