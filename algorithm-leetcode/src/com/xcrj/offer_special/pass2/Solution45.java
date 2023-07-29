package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 */
public class Solution45 {

    int maxHeight;
    int mostLeftVal;
    /**
     * 深度优先 后序遍历(左右根) 将首次到大啊更大高度的结点的值记录
     * @param root
     * @return
     */
    public int findBottomLeftValue1(TreeNode root) {
        dfs(root,0);
        return mostLeftVal;
    }

    private void dfs(TreeNode node,int height){
        if(null==node) return;

        height++;
        if(node.left!=null) dfs(node.left,height);
        if(node.right!=null) dfs(node.right,height);
        if(maxHeight<height){
            maxHeight=height;
            mostLeftVal=node.val;
        }
    }

    /**
     * 广度优先先入对右结点
     * 这样最后出队的一定是最下层最左侧结点
     * @param root
     * @return
     */
    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> que=new ArrayDeque<>();
        TreeNode p=root;
        que.offer(p);
        while(!que.isEmpty()){
            // xcrj 每次出队的结点都记录下来，则最后出队的结点也会被记录下来
            p=que.poll();
            if(p.right!=null) que.offer(p.right);
            if(p.left!=null)que.offer(p.left);
        }
        return p.val;
    }
}
