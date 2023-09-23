package com.xcrj.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 * 两个数组的交集
 * [2,2,2] [2]
 * [2]
 */
public class Main2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        // cnts1[i] cnts2[i] 都含有的num
        int[] cnts1 = new int[1001];
        int[] cnts2 = new int[1001];

        for (int num1 :
                nums1) {
            cnts1[num1]++;
        }

        for (int num2 :
                nums2) {
            cnts2[num2]++;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1001; i++) {
            if (cnts1[i] != 0 && cnts2[i] != 0) {
                list.add(i);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num1 :
                nums1) {
            set.add(num1);
        }

        List<Integer> list = new ArrayList<>();
        for (int num2 :
                nums2) {
            // num2在set中已经存在
            if (set.contains(num2)&&!list.contains(num2)) {// !list.contains(num2) 添加1个即可
                list.add(num2);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
