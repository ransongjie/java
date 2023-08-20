package com.xcrj.interview150.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 * 二叉树的右视图
 */
public class Solution10 {
    /**
     * 广度优先，一次出队一层结点，记录最后出队的结点
     * 最后出队结点就是 右侧视图结点
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(null==root) return list;

        Queue<TreeNode> que=new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()){
            int size=que.size();
            TreeNode p=null;
            while(size>0){
                p=que.poll();
                if(p.left!=null) que.offer(p.left);
                if(p.right!=null) que.offer(p.right);
                size--;
            }
            // 记录最后出队结点
            list.add(p.val);
        }

        return list;
    }
}
