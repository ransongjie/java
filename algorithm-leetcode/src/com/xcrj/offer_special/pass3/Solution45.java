package com.xcrj.offer_special.pass3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 */
public class Solution45 {
    int maxH;
    int maxLV;
    /**
     * 深度优先+最大层数 后序遍历 左右根
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        dfs(root,0);
        return maxLV;
    }

    private void dfs(TreeNode node,int height){
        if(node==null){
            return;
        }
        //xcrj
        height++;
        //
        if(node.left!=null)dfs(node.left, height);
        if(node.right!=null)dfs(node.right,height);
        if(height>maxH){
            maxH=height;
            maxLV=node.val;
        }
    }

    /**
     * 广度优先，先入队右子结点，如此最后出队的结点是最左下的结点
     * @param root
     * @return
     */
    public int findBottomLeftValue1(TreeNode root) {
        int r=0;
        //
        if(root==null) return 0;
        //
        Queue<TreeNode> que=new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            TreeNode p=que.poll();
            r=p.val;
            if(p.right!=null) que.offer(p.right);
            if(p.left!=null) que.offer(p.left);
        }
        //
        return r;
    }
}
