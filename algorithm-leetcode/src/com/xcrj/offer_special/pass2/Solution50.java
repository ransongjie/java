package com.xcrj.offer_special.pass2;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Solution50 {
    /**
     * 深度优先 前序遍历
     * - 根结点满足要求的路径和+左子树满足要求的路径和+右子树满足要求的路径和
     * @param root
     * @param targetSum long类型
     * @return
     */
    public int pathSum1(TreeNode root, long targetSum) {
        if(root ==null)return 0;

        // 从根结点往下
        int r=rootSum(root, targetSum);
        // 从左孩子往下
        r+=pathSum1(root.left, targetSum);
        // 从右孩子往下
        r+=pathSum1(root.right, targetSum);
        return r;
    }

    /**
     * 深度优先前序遍历，寻找更新的targetSum等于root.val
     * - targetSum-root.val
     * @param root
     * @param targetSum
     * @return
     */
    public int rootSum(TreeNode root, long targetSum) {
        if(root==null)return 0;

        int r=0;
        if(targetSum==root.val){
            r++;
        }
        // xcrj 不直接返回 结点值可以为0
        r+=rootSum(root.left, targetSum-root.val);
        r+=rootSum(root.right, targetSum-root.val);
        return r;
    }

    /**
     * 前缀和+回溯
     * - preSum+targetSum=sumI
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum2(TreeNode root, long targetSum) {
        // map<前缀和,次数>，某个前缀和出现的次数
        Map<Long, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0l, 1);
        return dfs(root, targetSum, preSumMap, 0);
    }

    /**
     * 回溯法
     * xcrj 在深度优先的过程中将前缀和和对应次数存储到map中
     * xcrj 从前缀和map中获取等于targetSum的次数
     * @return
     */
    private int dfs(TreeNode root, long targetSum,Map<Long, Integer> preSumMap,long preSum){
        if(root==null) return 0;

        long preSumI=preSum+root.val;
        // xcrj 先求r再更新preSumI的次数 因为在子树中进行了计算
        int r=preSumMap.getOrDefault(preSumI-targetSum, 0);
        // 更新
        preSumMap.put(preSumI, preSumMap.getOrDefault(preSumI,0)+1);
        r+=dfs(root.left,targetSum,preSumMap,preSumI);
        r+=dfs(root.right,targetSum,preSumMap,preSumI);
        // 回溯
        preSumMap.put(preSumI, preSumMap.getOrDefault(preSumI,0)-1);
        return r;
    }
}
