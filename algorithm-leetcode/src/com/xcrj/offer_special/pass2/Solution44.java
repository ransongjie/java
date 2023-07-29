package com.xcrj.offer_special.pass2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 */
public class Solution44 {
    /**
     * 前序遍历（根左右） 深度优先
     * - 到达新的层直接往List<层最大值>[层高]=存储值
     * - 不是新的层Max(List<层最大值>[层高], p.val)
     * @param root
     * @return
     */
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(null==root) return list;

        dfs(root,list,0);
        return list;
    }
    
    /**
     * 
     * @param node
     * @param levelMaxVals size等于层数
     * @param level
     */
    private void dfs(TreeNode node,List<Integer> levelMaxVals,int level){
        // 根
        // xcrj 深度优先一直往下，每到新的一层，添加一个值
        if(level==levelMaxVals.size()){
            levelMaxVals.add(node.val);
        }else{
            int maxVal=levelMaxVals.get(level);
            maxVal=Math.max(maxVal, node.val);
            levelMaxVals.set(level, maxVal);
        }
        // 左
        if(node.left!=null) dfs(node.left,levelMaxVals,level+1);
        // 右
        if(node.right!=null) dfs(node.right,levelMaxVals,level+1);
    }

    /**
     * 广度优先遍历
     * - 每次将同层的所有结点全部出队，将孩子结点全部入队
     * - 出队所有结点即出队que.size()个结点
     */
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(null==root) return list;
        
        Queue<TreeNode> que=new ArrayDeque<>();
        que.offer(root);
        while(!que.isEmpty()){
            int size=que.size();
            int maxVal=Integer.MIN_VALUE;
            while(size>0){
                TreeNode node=que.poll();
                maxVal=Math.max(maxVal, node.val);

                if(node.left!=null)que.offer(node.left);
                if(node.right!=null)que.offer(node.right);
                size--;
            }
            list.add(maxVal);
        }

        return list;
    }
}
