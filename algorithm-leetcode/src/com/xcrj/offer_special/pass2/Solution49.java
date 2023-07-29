package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * - 高度为3的二叉树，第一层根节点的值=根节点数字*100=A  第二层1个结点的值=A+数字*10=B 第三层1个结点的值=B+数字=C
 * - 子结点sum=父节点sum*10+子节点值，到叶子结点结束
 */
public class Solution49 {
    /**
     * 深度优先 前序遍历
     * @param root
     * @return
     */
    public int sumNumbers1(TreeNode root) {
        return dfs(root,0);
    }

    private int dfs(TreeNode node,int sum){
        if(node==null) return 0;

        sum=sum*10+node.val;
        // 当前结点sum已经求出，当前结点是叶子结点直接返回sum
        if(node.left==null&&node.right==null){
            return sum;
        }else{
            return dfs(node.left,sum)+dfs(node.right,sum);
        }
    }

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public int sumNumbers2(TreeNode root) {
        if(root==null) return 0;

        Queue<TreeNode> nodeQue=new ArrayDeque<>();
        Queue<Integer> sumQue=new ArrayDeque<>();
        nodeQue.offer(root);
        sumQue.offer(root.val);

        int sum=0;
        while(!nodeQue.isEmpty()){
            TreeNode node=nodeQue.poll();
            Integer pSum=sumQue.poll();

            if(node.left==null&&node.right==null){
                // xcrj sum求每条到叶子结点路径和之和
                // 叶子结点
                sum+=pSum;
            }else{
                if(node.left!=null){
                    nodeQue.offer(node.left);
                    // xcrj pSum 每条到叶子结点路径和
                    // 非叶子结点
                    sumQue.offer(pSum*10+node.left.val);
                }
                if(node.right!=null){
                    nodeQue.offer(node.right);
                    sumQue.offer(pSum*10+node.right.val);
                }
            }
        }

        return sum;
    }
}
