package com.xcrj.offer_special.pass1;

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
     * 逆向思维假设法+hash表
     * r= a_i ⊕ a_j 等价于 a_i = r ⊕ a_j
     * - 逆向思维从最高位（保证最大值）开始假设为1。
     * - 缩小可行解范围范围，在通过假设最高位为1而得到的可行解范围内寻找满足次高位为1的可行解以此类推
     */
    public int findMaximumXOR1(int[] nums) {
        int r = 0;
        // 以>>右移运算符的方式逐步处理最高位
        // >> 右移i步
        for (int i = 30; i >= 0; i--) {
            // 处理a_i，将a_i放入set中
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num >> i);
            }

            // 处理r，假设第i位为1，补充：因为r*2相当于<<左移，最开始的1在循环之后到达最高位
            int rNext = r * 2 + 1;
            boolean found = false;

            // 处理a_i = r ⊕ a_j，检查set中是否包含 r ⊕ a_j的运算结果a_i
            for (int num : nums) {
                if (set.contains(rNext ^ (num >> i))) {
                    found = true;
                    break;
                }
            }

            // 处理r，假设是否成立
            if (found) {
                r = rNext;
            } else {
                r = rNext - 1;
            }
        }

        return r;
    }

    // 字典树根节点
    Trie root = new Trie();

    /**
     * 字典树
     * - r= a_i ⊕ a_j
     * - 处理a_i，将nums[i]的每一位添加字典树中，i从0~n-1
     * - 处理a_j，检查nums[i]的每一位，为0则尝试走字典树右子结点（代表1），为1则尝试走字典树左孩子结点（代表0）
     */
    public int findMaximumXOR2(int[] nums) {
        int r = 0;
        for (int i = 1; i < nums.length; i++) {
            // 添加nums[0]~nums[len-2]到字典树中
            add(nums[i - 1]);
            // 获取最大r，nums[1]~nums[len-1]
            r = Math.max(r, getMaxR(nums[i]));
        }
        return r;
    }

    /**
     * 处理a_i，将nums[i]的每一位添加字典树中，i从0~n-1
     */
    private void add(int ai) {
        Trie node = this.root;
        // 以>>右移运算符的方式逐步处理最高位
        // >> 右移i步
        for (int i = 30; i >= 0; i--) {
            int biti = (ai >> i) & 1;
            // 为0则创建左结点
            if (0 == biti) {
                if (null == node.leftChild) node.leftChild = new Trie();
                node = node.leftChild;
            }
            // 为1则创建右结点
            else {
                if (null == node.rightChild) node.rightChild = new Trie();
                node = node.rightChild;
            }
        }
    }

    /**
     * 获取通过当前字典树能够获取的最大r
     */
    private int getMaxR(int aj) {
        Trie node = this.root;
        int r = 0;
        // 以>>右移运算符的方式逐步处理最高位
        // >> 右移i步
        for (int i = 30; i >= 0; i--) {
            int biti = (aj >> i) & 1;
            // 为0则尝试走右孩子结点（代表1）
            if (0 == biti) {
                // 右孩子不为空，则走右孩子
                if (null != node.rightChild) {
                    // ！r *= 2 + 1，运算符优先级别，2+1优先于*=
                    r = r * 2 + 1;
                    node = node.rightChild;
                }
                // 右孩子为空，则只能走左孩子
                else {
                    r *= 2;
                    node = node.leftChild;
                }
            }
            // 为1则尝试走左孩子结点（代表0）
            else {
                // 左孩子不为空，则走左孩子
                if (null != node.leftChild) {
                    r = r * 2 + 1;
                    node = node.leftChild;
                }
                // 右孩子为空，则只能走右孩子
                else {
                    r *= 2;
                    node = node.rightChild;
                }
            }
        }

        return r;
    }

    class Trie {
        // 左孩子结点代表0
        Trie leftChild;
        // 右孩子结点代表1
        Trie rightChild;
    }
}
