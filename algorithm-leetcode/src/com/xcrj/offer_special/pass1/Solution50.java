package com.xcrj.offer_special.pass1;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class Solution50 {
    /**
     * 两个递归
     * - 路径总数递归：当前结点满足条件的路径数量之和=左孩子结点满足条件路径数量+右孩子满足条件路径数量
     * - 结点总数递归：当前结点值等于targetSum的结点数量之和=左孩子结点值等于targetSum的结点数量之和+右孩子值等于targetSum的结点数量之和
     *
     * 求满足条件的路径总数
     * - 深度优先保证路径向下
     * - 父节点满足"向下路径节点之和=targetSum"的路径数目=当前父节点满足条件路径数量+左孩子结点满足条件路径数量+右孩子满足条件路径数量
     */
    public int pathSum1(TreeNode root, int targetSum) {
        if (null == root) return 0;

        //
        int r = 0;
        // 父节点满足条件路径数量
        r += rootSum(root, targetSum);
        // 左孩子满足条件路径数量
        r += pathSum1(root.left, targetSum);
        // 右孩子满足条件路径数量
        r += pathSum1(root.right, targetSum);

        return r;
    }

    /**
     * 求是否达到条件
     * 向下路径上的结点值之和=targetSum
     */
    public int rootSum(TreeNode root, int targetSum) {
        if (null == root) return 0;

        int r = 0;
        // 满足条件的路径增加1条
        if (targetSum == root.val) {
            r++;
            // 不能return，例如 左子树结点之和为0，仍然满足结点之和=targetSum
//            return r;
        }
        r += rootSum(root.left, targetSum - root.val);
        r += rootSum(root.right, targetSum - root.val);
        return r;
    }

    /**
     * 利用深度优先的特点结合前缀和求解
     * <p>
     * 深度优先
     * - 每到达叶子结点时 一直往下
     * - 到达叶子结点时(左右子树都走完了) 往上会退一步
     * <p>
     * 前缀和：
     * - preSum[node_j]+targetSum=preSum[node_i]，node_j+1到node_i的和=target_sum
     * - 若preSum[node_i]-targetSum存在即preSum[node_j]存在，则有1条路径满足 node_j+1到node_i的和=target_sum
     */
    public int pathSum2(TreeNode root, int targetSum) {
        // map<前缀和,次数>，某个前缀和出现的次数
        Map<Long, Integer> preSumMap = new HashMap<>();
        // 0没有任何结点的前缀和为0
        preSumMap.put(0L, 1);
        // 0没有任何结点的前缀和为0
        return dfs(root, targetSum, preSumMap, 0);
    }

    public int dfs(TreeNode root, int targetSum, Map<Long, Integer> preSumMap, long preSum) {
        if (null == root) return 0;

        Long preSumi = preSum + root.val;
        // preSum[node_j]+targetSum=preSum[node_i]，获取preSum[node_j]前缀和存在的数目
        int r = preSumMap.getOrDefault(preSumi - targetSum, 0);
        // 此结点前缀和
        preSumMap.put(preSumi, preSumMap.getOrDefault(preSumi, 0) + 1);
        r += dfs(root.left, targetSum, preSumMap, preSumi);
        r += dfs(root.right, targetSum, preSumMap, preSumi);
        // 深度优先，将推出当前结点，
        preSumMap.put(preSumi, preSumMap.getOrDefault(preSumi, 0) - 1);

        return r;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.right = n2;
        TreeNode n3 = new TreeNode(3);
        n2.right = n3;
        Solution50 solution50 = new Solution50();
        System.out.println(solution50.pathSum1(n1, 3));

    }
}
