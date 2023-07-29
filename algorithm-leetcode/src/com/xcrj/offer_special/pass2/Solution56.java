package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 * 二叉搜索树中是否存在两个节点它们的值之和等于 k 。
 * 假设二叉搜索树中节点的值均唯一。
 */
public class Solution56 {

    Set<Integer> set=new HashSet<>();
    /**
     * 遍历过程中查看或转储set
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget1(TreeNode root, int k) {
        if(root==null) return false;

        if(set.contains(k-root.val)) return true;
        set.add(root.val);
        return findTarget1(root.left, k)||findTarget1(root.right, k);
    }

    /**
     * 广度优先遍历查看或转储到set
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget2(TreeNode root, int k) {
        Set<Integer> set=new HashSet<>();

        Queue<TreeNode> que=new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            TreeNode p=que.poll();
            if(set.contains(k-p.val)) return true;
            set.add(p.val);

            if(p.left!=null) que.offer(p.left);
            if(p.right!=null) que.offer(p.right);
        }
        return false;
    }

    /**
     * 利用二叉搜索树性质，中序遍历时递增有序序列
     * 深度优先中序遍历结果转储到list
     * 左右指针逼近k
     * - left=0; right=len-1
     * - left+right 值之和>k，right左移，值之和<k，left右移，left指针==right指针还没有找到结果，则返回false
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget3(TreeNode root, int k) {
        List<Integer> list=new ArrayList<>();
        dfs(root,list);
        
        int left=0;
        int right=list.size()-1;
        while(left<right){
            if(list.get(right)+list.get(left)==k)return true;
            if(list.get(right)+list.get(left)<k) left++;
            else right--;
        }

        return false;
    }
    private void dfs(TreeNode node,List<Integer> list){
        if(node==null) return;

        dfs(node.left,list);
        list.add(node.val);
        dfs(node.right,list);
    }

    /**
     * leftStack栈顶到底存储 中序遍历序列从左往右的值 从小到大的值
     * rightStack栈底到顶存储 中序遍历序列从右往左的值 从大到小的值
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget4(TreeNode root, int k) {
        if (null == root) return false;

        Stack<TreeNode> leftStack=new Stack<>();
        Stack<TreeNode> rightStack=new Stack<>();

        TreeNode pl=root;
        // xcrj 为了让pl记录最左侧的结点 外部栈先记录了root 内部全部为pl.left
        leftStack.push(pl);
        while(pl.left!=null){
            leftStack.push(pl.left);
            pl=pl.left;
        }

        TreeNode pr=root;
        rightStack.push(pr);
        while(pr.right!=null){
            rightStack.push(pr.right);
            pr=pr.right;
        }

        while(pl!=pr){
            if(pl.val+pr.val==k)return true;
            if(pl.val+pr.val<k) pl=getLeft(leftStack);
            else pr=getRight(rightStack);
        }

        return false;
    }

    /**
     * 返回当前结点的直接后继结点
     * 将直接后继结点的后继结点入栈
     * @param sL
     * @return
     */
    public TreeNode getLeft(Stack<TreeNode> sL) {
        TreeNode root=sL.pop();
        TreeNode node=root.right;
        while(node!=null){
            sL.push(node);
            node=node.left;
        }
        return root;
    }

    /**
     * 返回当前结点的直接后继结点
     * 将直接后继结点的后继结点入栈
     * @param sL
     * @return
     */
    public TreeNode getRight(Stack<TreeNode> sR) {
        TreeNode root=sR.pop();
        TreeNode node=root.left;
        while(node!=null){
            sR.push(node);
            node=node.right;
        }

        return root;
    }
}
