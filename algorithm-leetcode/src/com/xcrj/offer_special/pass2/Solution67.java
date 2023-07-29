package com.xcrj.offer_special.pass2;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer II 067. 最大的异或结果
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1，即nums[]数组中的每个值最大为 0111,1111,24个1。可以采用int值求解
 */
public class Solution67 {
    /**
     * 期望是，实际情况
     * 
     * 问题转换
     * - nums[i] XOR nums[j]=r max
     * - nums[i] XOR nums[j]=r, nums[i] =r XOR nums[j]
     * 
     * xcrj 能否找到让现在的r(实际)的下一位为1(期望) XOR nums[j]=nums[i]
     * 期望r每一位都为1>期望r高位尽可能为1>从高位往低位依次期望
     * - 期望r第2位为1，看能否找到nums[i]，确定r的第2位
     * - 实际r的第2位+期望r第3位为1，看能否找到nums[i]，确定r的第3位
     * - 实际r的第2~3位+期望r第4位为1，看能否找到nums[i]，确定r的第4位
     * - ...
     * - 实际r的第2~31位+期望r第32位为1，看能否找到nums[i]，确定r的第32位
     * 
     * @param nums
     * @return
     */
    public int findMaximumXOR1(int[] nums) {
        int r=0;
        // 30，第2位移动到倒数第1位
        for(int i=30;i>=0;i--){
            // 所有的数第i位的结点放入set中
            // nums[i]
            Set<Integer> set=new HashSet<>();
            for(int num:nums){
                set.add(num>>i);
            }

            // 期望r的下一位为1，*2相当于左移
            int rNext=r*2+1;

            // set中是否存在nums[i]
            // nums[i] =r XOR nums[j]
            boolean exsited=false;
            for(int num:nums){
                if(set.contains(rNext^(num>>i))){
                    exsited=true;
                    break;
                }
            }

            // 实际情况是否满足
            if(exsited) r=rNext;
            else r=rNext-1;
        }

        return r;
    }

    /**
     * 使用二进制位构建二叉字典树
     * 从高位到低位,左0右1,构造二叉字典树
     * @param nums
     * @return
     */
    public int findMaximumXOR2(int[] nums) {
        int maxR=0;
        // xcrj n个数两两异或 组合数 C^2_n={n!}/{(n-2)!*2!}
        /**
         * xcrj 
         * 0 XOR 1
         * 0 XOR 2, 1 XOR 2
         * 0 XOR 3, 1 XOR 3, 2 XOR 3
         * ...
         * 0 XOR n, 1 XOR n, 2 XOR n
         */
        for(int i=1;i<nums.length;i++){
            // 为ai构建字典树
            add(nums[i-1]);
            // aj XOR 现有字典树(ai)=r
            maxR=Math.max(maxR,getR(nums[i]));
        }
        return maxR;
    }

    /**
     * 根据整数bit位为0还是1创建二叉字典树
     * 从高位到低位建立二叉字典树
     * @param ai
     */
    private void add(int ai) {
        Trie node=root;
        for(int i=30;i>=0;i--){
            int ibit=(ai>>i)&1;
            if(0==ibit){
                if(null==node.left) node.left=new Trie();
                node=node.left;
            }else{
                if(null==node.right) node.right=new Trie();
                node=node.right;
            }
        }
    }

    /**
     * 通过当前字典树(ai构成) XOR aj=能够得到的最大r
     */
    private int getR(int aj) {
        Trie node=root;
        int r=0;
        for(int j=30;j>=0;j--){
            int jbit=(aj>>j)&1;
            // ajbit
            // aj为0，ai为1，r才能获得最大值
            if(0==jbit){
                // aibit
                if(node.right!=null){
                    // xcrj r*=2+1 不等于 r=r*2+1;
                    r=r*2+1;
                    node=node.right;
                }
                else{
                    r*=2;
                    node=node.left;
                }
            }else{
                if(node.left!=null){
                    r=r*2+1;
                    node=node.left;
                }else{
                    r*=2;
                    node=node.right;
                }
            }
        }

        return r;
    }

    class Trie{
        Trie left;
        Trie right;
    }

    Trie root=new Trie();
}
