package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 079. 所有子集，幂集
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Solution79 {

    List<List<Integer>> rss = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    /**
     * 迭代法+二进制编码
     * 
     * 例如：
     * 3 2 1
     * 0 0 0
     * 第1位2种情况（选/不选） 第2位2种情况（选/不选） 第3位2种情况（选/不选）
     * 共有情况：2*2*2=1<<3
     * 问题转换：遍历n位2进制数的所有情况并输出对应位置的原数字
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        // 共有多少种选择情况
        int k=1<<nums.length;
        // 遍历每一种情况，获取二进制编码代表的源数字
        for(int i=0;i<k;i++){
            list.clear();
            // 获取i的每一位，为1代表选择对应位的值，为0代表不选择对应位的值
            for(int j=0;j<nums.length;j++){
                // 判断i的第j位是否为0
                if(((1<<j)&i)!=0) list.add(nums[j]);
            }
            // xcrj new ArrayList<>(list) 因为list本身是引用
            rss.add(new ArrayList<>(list));
        }
        return rss;
    }

    /**
     * 回溯法
     * - 深度优先搜索，回溯，解空间树，剪枝
     * - 选择回溯不选择
     */
    public List<List<Integer>> subsets2(int[] nums) {
        // 选择0个数
        dfs(nums,0);
        return rss;
    }

    private void dfs(int[] nums, int i) {
        // 选择到了nums.len个数
        if(i==nums.length){
            rss.add(new ArrayList<>(list));
            return;
        }

        // 选择第i个数
        list.add(nums[i]);
        // 处理下1个数
        dfs(nums,i+1);
        // 不选择第i个数
        list.remove(list.size()-1);
        // 处理下1个数
        dfs(nums,i+1);
    }

    /**
     * 增量蛮力法+递归
     * 在原集合中加入新的值构成增量集合，再将增量集合放入原集合中
     * <p>
     * 求解幂集
     * - 幂集问题是一个组合问题
     * - 空集和全集也属于幂集
     * <p>
     * 增量蛮力法：把新数字增加到原来集合的所有幂集中+原来集合中的所有幂集=新的集合
     * {{}}
     * {{},{1}}
     * {{},{1},{2},{1,2}}
     * {{},{1},{2},{1,2},{3},{1,3},{2,3},{1,2,3}}
     */
    public List<List<Integer>> subsets3(int[] nums) {
        // 初始加入空集合
        rss.add(new ArrayList<>());
        powerSet(nums, 0);
        return rss;
    }

    /**
     * @param nums
     * @param i    处理第i个数
     */
    private void powerSet(int[] nums, int i) {
        if(i<nums.length){
            insertI(nums[i]);
            powerSet(nums, i+1);
        }
    }

    /**
     * 增量插入
     * @param v 元素值
     */
    private void insertI(int v) {
        // 增量集合：对原集合中的每个子集合添加v构成新集合
        List<List<Integer>> addss=new ArrayList<>();
        for(List<Integer> rs:rss){
            List<Integer> adds=new ArrayList<>(rs);
            adds.add(v);
            addss.add(adds);
        }
        // 将增量集合添加到原集合
        for(List<Integer> adds: addss){
            rss.add(new ArrayList<>(adds));
        }
    }
}
