package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 046. 二叉树的右侧视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 先看到右侧结点，右侧结点不存在则看到左侧结点
 * 每层1个结点
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
     * 每层最后遍历的结点
     * 每个深度放置1个结点
     */
    public List<Integer> rightSideView2(TreeNode root) {
        // 结果
        List<Integer> rs = new ArrayList<>();

        // 特殊情况处理
        if (null == root) return rs;

        // 广度优先所需队列结构
        Queue<TreeNode> tQue = new ArrayDeque<>();
        // 记录结点对应的深度
        Queue<Integer> dQue = new ArrayDeque<>();
        // 每个深度记录1个结点
        Map<Integer, Integer> depVal = new HashMap<>();
        // 记录最大深度
        int maxDepth = Integer.MIN_VALUE;

        // 初始化
        tQue.offer(root);
        dQue.offer(1);

        // 广度优先
        while (!tQue.isEmpty()) {
            TreeNode p = tQue.poll();
            int depth = dQue.poll();
            maxDepth = Math.max(depth, maxDepth);
            // 每个深度1个结点，每层最后出队的结点才会是存储到map中的值
            depVal.put(depth, p.val);

            if (null != p.left) {
                tQue.offer(p.left);
                dQue.offer(depth + 1);
            }
            if (null != p.right) {
                tQue.offer(p.right);
                dQue.offer(depth + 1);
            }
        }

        // 处理结果
        for (int i = 1; i <= maxDepth; i++) {
            rs.add(depVal.get(i));
        }

        return rs;
    }

    /**
     * 广度优先
     * 每次出队1层的结点，记录最后出队的结点
     */
    public List<Integer> rightSideView3(TreeNode root) {
        // 结果
        List<Integer> rs = new ArrayList<>();

        // 特殊情况处理
        if (null == root) return rs;

        // 广度优先所需队列结构
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 记录每层的结点个数
        int numL = 1;

        // 初始化
        queue.offer(root);

        // 广度优先
        while (!queue.isEmpty()) {
            // 一次性出队一层的结点
            TreeNode p = null;
            int num = 0;
            while (numL > 0) {
                p = queue.poll();
                if (null != p.left) {
                    queue.offer(p.left);
                    num++;
                }
                if (null != p.right) {
                    queue.offer(p.right);
                    num++;
                }

                numL--;
            }
            rs.add(p.val);
            numL = num;
        }

        return rs;
    }
}
