package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 079. 所有子集，幂集
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Solution79 {

    /**
     * 迭代法+二进制编码
     * 将每种情况编码到二进制表示中，0代表不选择，1代表选择
     * <p>
     * 求解幂集
     * 对于给定的正整数n（n≥1），求1～n构成的集合的所有子集（幂集）。
     * 幂集问题是一个组合问题
     * 有n个正整数，他们从1到n，
     * 幂集：空集和全集也属于幂集
     * 问题转换：对于每个数都只有选择或不选择两种情况，转成二进制位，0-不选择，1-选择
     * 例如：
     * 3 2 1
     * 0 0 0
     * <p>
     * 第1位2种情况（选/不选） 第2位2种情况（选/不选） 第3位2种情况（选/不选）
     * 共有情况：2*2*2
     * 问题转换：遍历n位2进制数的所有情况并输出对应位置的原数字
     */
    public List<List<Integer>> subsets1(int[] nums) {
        // 共有多少种选择情况
        int sumNum = 1 << nums.length;

        // 遍历每一种情况，获取二进制编码代表的源数字
        for (int i = 0; i < sumNum; i++) {
            list.clear();
            // 获取i的每一位，为1代表选择对应位的值，为0代表不选择对应位的值
            for (int j = 0; j < nums.length; j++) {
                // 判断i的第j位是否为0
                if ((i & (1 << j)) != 0) {
                    list.add(nums[j]);
                }
            }

            rss.add(new ArrayList<>(list));
        }

        return rss;
    }

    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 解空间树
     * - 子集树：达到要求的子集，元素子集。选择某个元素/不选择某个元素
     * <p>
     * 回溯法
     * - nums[0]选择不选择
     * - nums[1]选择不选择
     * - nums[2]选择不选择
     */
    public List<List<Integer>> subsets2(int[] nums) {
        // 0开始没有选择任何数
        dfs(nums, 0);
        return rss;
    }

    /**
     * @param nums 解数组
     * @param i    第i个数（选择不选择）
     */
    private void dfs(int[] nums, int i) {
        // 处理（选择/不选择）完这nums.length个数
        if (i == nums.length) {
            rss.add(new ArrayList<>(list));
            return;
        }

        // ！选择第i个数
        list.add(nums[i]);
        // 处理第i+1个数
        dfs(nums, i + 1);
        // ！回溯，不选择第i个数
        list.remove(list.size() - 1);
        // 再处理第i+1个数
        dfs(nums, i + 1);
    }

    /**
     * 增量蛮力法+递归
     * <p>
     * 求解幂集
     * 对于给定的正整数n（n≥1），求1～n构成的集合的所有子集（幂集）。
     * 幂集问题是一个组合问题
     * 有n个正整数，他们从1到n，
     * 幂集：空集和全集也属于幂集
     * <p>
     * 增量蛮力法：把新数字增加到原来集合的所有幂集中+原来集合中的所有幂集=新的集合
     * {{}}
     * {{},{1}}
     * {{},{1},{2},{1,2}}
     * {{},{1},{2},{1,2},{3},{1,3},{2,3},{1,2,3}}
     * <p>
     * 递归求解:
     * 递归出口：要增加的数i>最大数n
     * 递归体：每次给原有幂集增加i
     */
    public List<List<Integer>> subsets3(int[] nums) {
        // 初始添加空集
        this.rss.add(new ArrayList<>());
        powerSet(nums, 0);
        return rss;
    }

    /**
     * @param nums
     * @param i    处理第i个数
     */
    private void powerSet(int[] nums, int i) {
        if (i < nums.length) {
            insertI(nums[i]);
            powerSet(nums, i + 1);
        }
    }

    /**
     * 增量插入
     *
     * @param v 元素值
     */
    private void insertI(int v) {
        // 增量集合：对源集合中的每个子集合添加v构成新集合
        List<List<Integer>> addedss = new ArrayList<>();
        for (List<Integer> rs : rss) {
            // 深拷贝源子集合
            List<Integer> addeds = new ArrayList<>(rs);
            // 为子集合添加新值
            addeds.add(v);
            // 将增量子集合添加到增量集合
            addedss.add(addeds);
        }

        // 结果集合：将增量集合添加到源集合
        for (List<Integer> addeds : addedss) {
            rss.add(addeds);
        }
    }
}
