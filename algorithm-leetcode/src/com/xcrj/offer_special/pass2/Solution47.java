package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树根节点root，树的每个节点的值要么是0，要么是1。
 * 请剪除该二叉树中值为0的叶子结点
 */
public class Solution47 {
    /**
     * 深度优先 后续遍历的过程中剪枝
     * 剪枝左右结点不存在且根结点值为0
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if(null==root) return null;
        root.left=pruneTree(root.left);
        root.right=pruneTree(root.right);
        if(root.left==null&&root.right==null&&root.val==0){
            return null;
        }
        return root;
    }
}
