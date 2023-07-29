package com.xcrj.offer_special.pass2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点
 */
public class Solution43 {
    /**
     * 使用队列存储候选结点
     */
    class CBTInserter1 {
        Queue<TreeNode> condiQue;
        TreeNode troot;
        /**
         * 初始化
         * 获取候选结点
         * @param root
         */
        public CBTInserter1(TreeNode root) {
            troot=root;
            condiQue=new ArrayDeque<>();

            // 广度优先遍历
            Queue<TreeNode> que=new ArrayDeque<>();
            que.offer(root);
            while(!que.isEmpty()){
                TreeNode node=que.poll();
                if(node.left!=null) que.offer(node.left);
                if(node.right!=null)que.offer(node.right);
                // xcrj
                if(!(node.left!=null&&node.right!=null)) condiQue.offer(node);
            }
        }

        /**
         * 插入新结点
         * @param v
         * @return 父节点的值
         */
        public int insert(int v) {
            TreeNode node=new TreeNode(v);
            // xcrj 没有可插入位置之后再poll()
            TreeNode parent=condiQue.peek();
            if(parent.left==null) parent.left=node;
            else if(parent.right==null) {
                parent.right=node;
                // 
                condiQue.poll();
            };

            condiQue.offer(node);

            return parent.val;
        }

        /**
         * 获取根结点
         * @return
         */
        public TreeNode get_root() {
            return troot;
        }
    }

    /**
     * 广度优先遍历结点序号的二进制表示代表了结点插入的位置
     */
    class CBTInserter2 {
        TreeNode troot;
        int order;
        /**
         * 初始化
         * @param root
         */
        public CBTInserter2(TreeNode root) {
            troot=root;

            Queue<TreeNode> que=new ArrayDeque<>();
            que.offer(root);
            while(!que.isEmpty()){
                TreeNode node=que.poll();
                if(node.left!=null) que.offer(node.left);
                if(node.right!=null)que.offer(node.right);
                order++;
            }
        }

        /**
         * 插入新结点
         * @param v
         * @return 父节点的值
         */
        public int insert(int v) {
            // 新结点
            TreeNode node=new TreeNode(v);
            order++;

            int numBit=32-Integer.numberOfLeadingZeros(order);
            TreeNode p=troot;
            // order中间二进制表示 不看两端
            for(int i=numBit-2;i>=1;i--){
                if((order&1<<i)==0)p=p.left;
                else p=p.right;
            }

            // order最后1位的二进制表示
            if((order&1)==0)p.left=node;
            else p.right=node;
            
            return p.val;
        }

         /**
         * 获取根结点
         * @return
         */
        public TreeNode get_root() {
            return troot;
        }
    }
}
