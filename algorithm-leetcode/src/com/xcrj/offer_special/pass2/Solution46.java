package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 */
public class Solution46 {
    /**
     * 迭代式深度优先遍历，先将左结点放入栈中，再将右结点放入栈中，
     * 每层存入1个结点值，先出栈的一定是靠右侧的结点
     * 记录最大高度
     * 
     * @param root
     * @return
     */
    public List<Integer> rightSideView1(TreeNode root) {
        // map<height,val>
        Map<Integer, Integer> map = new HashMap<>();

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> heightStack = new Stack<>();
        nodeStack.push(root);
        heightStack.push(0);

        int maxHeight = Integer.MIN_VALUE;
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int height = heightStack.pop();
            // xcrj
            if (node != null) {
                // 求最大深度
                maxHeight = Math.max(maxHeight, height);

                if (!map.containsKey(height))
                    map.put(height, node.val);

                nodeStack.push(node.left);
                heightStack.push(height + 1);

                nodeStack.push(node.right);
                heightStack.push(height + 1);

            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= maxHeight; i++) {
            list.add(map.get(i));
        }

        return list;
    }

    /**
     * 广度优先
     * 每层最后1个结点的值会覆盖每层前面结点的值
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;

        Queue<TreeNode> nodeQue=new ArrayDeque<>();
        Queue<Integer> heightQue=new ArrayDeque<>();
        nodeQue.offer(root);
        heightQue.offer(0);

        Map<Integer,Integer> map=new HashMap<>();
        int maxHeight=Integer.MIN_VALUE;
        while(!nodeQue.isEmpty()){
            TreeNode node=nodeQue.poll();
            int height=heightQue.poll();

            maxHeight=Math.max(maxHeight, height);

            map.put(height, node.val);

            if(node.left!=null){
                nodeQue.offer(node.left);
                heightQue.offer(height+1);
            }

            if(node.right!=null){
                nodeQue.offer(node.right);
                heightQue.offer(height+1);
            }
        }

        for (int i = 0; i <= maxHeight; i++) {
            list.add(map.get(i));
        }

        return list;
    }

    /**
     * 广度优先，一次出队一层结点，记录最后出队的结点
     */
    public List<Integer> rightSideView3(TreeNode root) {
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

            list.add(p.val);
        }

        return list;
    }
}
