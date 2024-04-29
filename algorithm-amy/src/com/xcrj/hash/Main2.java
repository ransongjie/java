package com.xcrj.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 * 两个数组的交集
 * [2,2,2] [2]
 * [2]
 */
public class Main2 {
    //set和nums2中都有的数字
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int n :
                nums1) {
            set.add(n);
        }

        ArrayList<Integer> ls = new ArrayList<>();
        for (int n :
                nums2) {
            if (set.contains(n) && !ls.contains(n)) {
                ls.add(n);
            }
        }

        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }

    //两个数组都有的数字
    public int[] intersection2(int[] nums1, int[] nums2) {
        int[] cnts1 = new int[1001];
        int[] cnts2 = new int[1001];
        for (int n :
                nums1) {
            cnts1[n]++;
        }
        for (int n :
                nums2) {
            cnts2[n]++;
        }

        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < cnts1.length; i++) {
            if (cnts1[i] != 0 && cnts2[i] != 0) {
                ls.add(i);//
            }
        }

        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        return ans;
    }
}
