package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 剑指 Offer II 057. 值和下标之差都在给定的范围内
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * nums[] 整数数组：存在 值差<=t && 下标差<=k
 */
public class Solution57 {

    /**
     * 滑动窗口+有序集合
     * 有序集合：保证滑动窗口内的元素是有序的
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        // 添加元素之后会自动排序，默认升序
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // y>=x-t
            // treeSet.ceiling返回大于等于给定元素的最近的元素
            Long ceiling = treeSet.ceiling((long) nums[i] - (long) t);
            // y<=x+t
            // long? (long) nums[i] + (long) t两个int类型的值相加可能超过integer.Max
            if (null != ceiling && ceiling <= ((long) nums[i] + (long) t)) return true;
            treeSet.add((long) nums[i]);
            // 移除窗口左侧的元素
            // k：滑动窗口大小为k。=k：i从0开始
            if (i >= k) treeSet.remove((long) nums[i - k]);
        }
        return false;
    }

    /**
     * 滑动窗口+桶排序思想
     * - 滑动窗口，限制索引差值 i-j<=k
     * - 桶容量，限制值差值 x-y<=t
     * - 桶编号，把x放到id=x/w的桶里面
     * <p>
     * 桶排序：类比学生按成绩等级排序
     * - 桶数量：学生成绩等级数量
     * - 桶编号：学生成绩等级，90到100分成绩是A等级存储到A等级桶，80到90分成绩是B等级存储到B等级桶，依次类推
     * - 桶容量：10分值
     * <p>
     * 桶排序思想：
     * - 桶数量：k
     * - 桶编号：x/w
     * - 桶容量：t+1，x/w（w是桶的容量，整体是桶的编号）
     * <p>
     * 详情：
     * - x影响范围为[x−t,x+t]，因此桶的大小为t+1
     * - 当两个元素属于桶一个桶，则两个元素符合条件x-y<=t
     * - 当两个元素属于相邻桶，则校验x-y<=t。例如，x在前1个桶的末尾，y在后1个桶的开头仍然可能满足x-y<=t
     * - 当两个元素不属于同一个桶&&不属于相邻桶，那么两个元素不满足条件x-y<=t。例如，y在第1个桶，x在第3个桶，2个相隔第2个桶，相隔w==t+1，x-y>=t+1
     */
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        // 创建桶
        // map做桶：1个桶中至多1个元素：如果同一个桶中有两个元素满足条件了，return了
        Map<Long, Long> bucketMap = new HashMap<>();
        // 桶容量
        long v = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getBucketID(nums[i], v);
            // 当两个元素属于桶一个桶，则两个元素符合条件x-y<=t
            if (bucketMap.containsKey(id)) return true;
            // 当两个元素属于相邻桶，则校验x-y<=t
            if (bucketMap.containsKey(id + 1) && Math.abs(nums[i] - bucketMap.get(id + 1)) <= t)
                return true;
            // 当两个元素不属于同一个桶&&不属于相邻桶
            if (bucketMap.containsKey(id - 1) && Math.abs(nums[i] - bucketMap.get(id - 1)) <= t)
                return true;

            bucketMap.put(id, (long) nums[i]);
            // 滑动窗口
            // k：滑动窗口大小为k。=k：i从0开始
            if (i >= k) bucketMap.remove(getBucketID(nums[i - k], v));
        }
        return false;
    }

    /**
     * 获取桶的编号
     */
    public long getBucketID(long x, long v) {
        if (x >= 0) return x / v;
            // x属于[-(t+1),-1]时，应该放到id=-1的桶里面
        else return (x + 1) / v - 1;
    }
}
