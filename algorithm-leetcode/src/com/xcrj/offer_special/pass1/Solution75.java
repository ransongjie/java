package com.xcrj.offer_special.pass1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 075. 数组相对排序
 * 给定两个数组，arr1 和 arr2，
 * - arr2 中的元素各不相同
 * - arr2 中的每个元素都出现在 arr1 中
 * - 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * - 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * 提示
 * - 1 <= arr1.length, arr2.length <= 1000
 * - 0 <= arr1[i], arr2[i] <= 1000
 * - arr2中的元素arr2[i]各不相同
 * - arr2 中的每个元素arr2[i]都出现在arr1中
 * <p>
 * 分析：
 * - arr1中在arr2的元素顺序和arr2保持一致
 * - arr1中不在arr2的元素升序放到末尾
 */
public class Solution75 {
    /**
     * 自定义排序
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {
        // 将arr2放到hash表中 map<值,顺序>
        Map<Integer, Integer> map = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            int e2 = arr2[i];
            map.put(e2, i);
        }
        // Array.sort(必须引用类型,)
        // ！int[]》Integer[]
        Integer[] arrI1 = Arrays.stream(arr1).boxed().toArray(Integer[]::new);
        int[] rs = new int[arr1.length];
        /**
         *  new Comparator<Integer>(){public int compare(Integer o1, Integer o2)}
         *  - 返回负数，o1<o2，o1放到o2前面
         *  - 返回正数，o1>o2，o1放到o2后面
         *  - 返回0，o1=o2，无先后顺序
         */

        /**
         * 自定义比较器
         * - pre和next都在arr2中，arr1根据arr2中元素出现顺序排序
         * - pre和next都不在arr2中，arr1根据arr1中元素大小排序
         * - pre和next只有1个在arr2中，直接返回0，无先后顺序
         *
         * ！为什么return 0就放到arr1序列尾部了：todo
         * - 因为最终只有2中情况 都在arr2中、都不在arr2中
         * - 都在arr2中，根据arr2中元素出现顺序排序
         * - 都不在arr2中，根据arr1中元素大小排序
         */
        Arrays.sort(arrI1, new Comparator<Integer>() {
            @Override
            public int compare(Integer pre, Integer next) {
                // pre和next都在arr2中，arr1根据arr2中元素出现顺序排序
                // arr1中的两个值都在arr2中。arr1中的pre值在arr2中存在，next值在arr2中存在，则按照map<,rank>rank排序
                if (map.containsKey(pre) && map.containsKey(next)) {
                    return map.get(pre) - map.get(next);
                }
                // pre和next都不在arr2中，arr1根据arr1中元素大小排序
                // arr1中的pre值在arr2中不存在，next值在arr2中不存在，则按照pre值和next值的大小排序
                if (!map.containsKey(pre) && !map.containsKey(next)) {
                    return pre - next;
                }
                //
                // arr1中的pre值在arr2中存在，next值在arr2中不存在，则直接返回true
                if (map.containsKey(pre) && !map.containsKey(next)) {
                    return 0;
                }
                // arr1中的pre值在arr2中不存在，next值在arr2中存在，则直接返回false
                if (!map.containsKey(pre) && map.containsKey(next)) {
                    return 0;
                }

                return 0;
            }
        });

        // ! Integer[]》int[]
        return Arrays.stream(arrI1).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 计数排序：元素出现次数计数器数组 frequency[] 下标是arr1中的元素，值是arr1中元素出现的次数
     * - 确定frequency[]的长度为arr1[]中最大值+1，因为frequency[maxValue]不能越界，数组下标从0开始
     * - 统计arr1中每个元素值出现的次数
     * - 顺序遍历arr2，将arr2的元素x添加到rs[]中frequency[x]次，后把frequency[x]=0
     * - 遍历frequency[]，将frequency[x]!=0的下标x放到rs[]末尾
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        // 确定frequency[]的长度为arr1[]中最大值+1，因为frequency[maxValue]不能越界，数组下标从0开始
        int maxValue = 0;
        for (int a1 : arr1) {
            maxValue = Math.max(maxValue, a1);
        }
        int frequency[] = new int[maxValue + 1];
        // 统计arr1中每个元素值出现的次数
        for (int a1 : arr1) {
            frequency[a1]++;
        }
        // 顺序遍历arr2，将arr2的元素x添加到rs[]中frequency[x]次，后把frequency[x]=0
        int[] rs = new int[arr1.length];
        int idx = 0;
        for (int a2 : arr2) {
            for (int i = 0; i < frequency[a2]; i++) {
                rs[idx++] = a2;
            }
            frequency[a2] = 0;
        }
        // 遍历frequency[]，将frequency[x]!=0的下标x放到rs[]末尾
        for (int i = 0; i < frequency.length; i++) {
            // 添加frequency[]中剩余的出现次数不为0的下标x
            for (int j = 0; j < frequency[i]; j++) {
                rs[idx++] = i;
            }
        }

        return rs;
    }
}
