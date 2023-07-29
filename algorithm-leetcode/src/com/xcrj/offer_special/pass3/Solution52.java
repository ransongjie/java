package com.xcrj.offer_special.pass3;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 052. 二叉搜索树展平为右斜树
 * - 二叉搜索树就是二叉排序树
 * - 中序遍历二叉搜索数，将得到递增有序的序列
 * 
 * 按中序遍历将其重新排列为一棵递增顺序搜索树
 * - 树中最左边的节点成为树的根节点
 * - 每个节点没有左子节点，只有一个右子节点
 */
public class Solution52 {
    /**
     * 中序遍历二叉搜索结果放入链表中
     * 从链表中构建只有右结点的二叉树
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        //
        TreeNode dummy=new TreeNode(0);
        TreeNode p=dummy;
        //
        List<Integer> list=new ArrayList<>();
        dfs(root, list);
        //
        for(Integer val:list){
            TreeNode node=new TreeNode(val);
            p.right=node;
            p=node;
        }
        //
        return dummy.right;
    }
    private void dfs(TreeNode root,List<Integer> list){
        if(root==null) return;
        //
        dfs(root.left,list);
        list.add(root.val);
        dfs(root.right,list);
    }

    TreeNode p=null;
    /**
     * 在中序遍历的过程中构建树，类变量记录上一个结点
     * @param root
     * @return
     */
    public TreeNode increasingBST2(TreeNode root) {
        //
        TreeNode dummy=new TreeNode(0);
        p=dummy;
        //
        dfs(root);
        //
        return dummy.right;
    }
    private void dfs(TreeNode root){
        if(root==null) return;
        //
        dfs(root.left);
        p.right=root;
        root.left=null;
        p=root;
        dfs(root.right);
    }

}
