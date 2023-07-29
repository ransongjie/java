package com.xcrj.offer_special.pass2;
import java.util.Stack;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 */
public class Solution53 {
    /**
     * 迭代式深度优先中序遍历，使用双指针pre和cur记录，若pre=p则cur就是直接后继结点
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        TreeNode pre=null;
        TreeNode cur=root;
        Stack<TreeNode> stack=new Stack<>();
        // xcrj 右斜树时，stack为空，cur不为空
        while(!stack.isEmpty()||cur!=null){
            // 深度优先中序遍历
            while(cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }

            cur=stack.pop();
            if(pre==p) return cur;
            else pre=cur;

            cur=cur.right;
        }
        return null;
    }
    
    /**
     * 分情况讨论
     * - p存在右孩子，右孩子结点的最左边孩子是结果
     * - p不存在右孩子，p的后继节点是比p大的最左侧结点，比p小的最右侧结点，一个从根结点出发的夹逼的过程
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if(p.right!=null){
            TreeNode cur=p.right;
            while(cur.left!=null)cur=cur.left;
            return cur;
        }

        TreeNode successor=null;
        TreeNode cur=root;
        while(cur!=null){
            if(cur.val>p.val){
                // xcrj 每次successor都记录大于p的结点 最后无法再进一步夹逼后 successor就是p的直接后继结点
                successor=cur;
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }

        return successor;
    }
}
