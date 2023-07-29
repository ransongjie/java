package com.xcrj.basic.pass1.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaxWidth {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        Node node8=new Node(8);
        //
        node1.left=node2;node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;node3.right=null;
        node4.left=node4.right=null;node5.left=node7;node5.right=null;node6.left=node8;node6.right=null;
        node7.left=node7.right=null;node8.left=node8.right=null;
        //
        Node root=node1;
        // Util.printTree(root);
        //
        System.out.println(maxWidth(root));
        System.out.println(maxWidth2(root));
        System.out.println(maxWidth3(root));
    }

    /**
     * 宽度优先遍历，记录最大层数，记录Map<node,level>结点所在层数
     * @param root
     * @return
     */
    public static int maxWidth(Node root) {
        if(root==null) return 0;
        int maxNum=Integer.MIN_VALUE;
        Queue<Node> que=new LinkedList<>();
        Map<Node,Integer> nodeLevel=new HashMap<>();
        //
        int maxLevel=1;
        que.offer(root);
        nodeLevel.put(root,1);
        while(!que.isEmpty()){
            Node n=que.poll();
            int level=nodeLevel.get(n);
            if(level>maxLevel) maxLevel=level;
            if(n.left!=null){
                que.offer(n.left);
                nodeLevel.put(n.left,level+1);
            }
            if(n.right!=null){
                que.offer(n.right);
                nodeLevel.put(n.right,level+1);
            }
        }
        //
        for (int i = 1; i <= maxLevel; i++) {
            int num=0;  
            for(Integer v:nodeLevel.values()){
                if(i==v) num++;
            }  
            maxNum=Math.max(maxNum, num);
        }
        //
        return maxNum;
    }

    /**
     *
     * @param root
     * @return
     */
    public static int maxWidth2(Node root) {
        if(root==null) return 0;
        Queue<Node> que=new LinkedList<>();
        que.offer(root);
        //
        Map<Node,Integer> nodeLevel=new HashMap<>();
        nodeLevel.put(root,1);
        int maxNum=Integer.MIN_VALUE;
        int curLevel=1;
        int curLevelNum=0;
        //
        while(!que.isEmpty()){
            Node n=que.poll();
            //当前层有多少个结点
            int level=nodeLevel.get(n);
            if(level==curLevel){
                curLevelNum++;
            }else{
                maxNum=Math.max(maxNum, curLevelNum);
                curLevel=level;
                curLevelNum=1;
            }
            //
            if(n.left!=null) {
                que.offer(n.left);
                nodeLevel.put(n.left,level+1);
            }
            if(n.right!=null){
                que.offer(n.right);
                nodeLevel.put(n.right,level+1);
            } 
        }
        return maxNum;
    }

    /**
     * 广度优先遍历 每到一层的最后一个结点进行统计
     * curEnd：当前层最后一个结点
     * nxtEnd：下层最后一个结点
     * curNodeNum：当前层结点数量
     * @param root
     * @return
     */
    public static int maxWidth3(Node root) {
        if(root==null) return 0;
        Queue<Node> que=new LinkedList<>();
        que.offer(root);
        //
        int max=Integer.MIN_VALUE;
        Node curEnd=root;
        Node nxtEnd=null;
        int curNodeNum=0;
        //
        while(!que.isEmpty()){
            Node node=que.poll();
            curNodeNum++;
            //
            if(node.left!=null){
                que.offer(node.left);
                nxtEnd=node.left;
            }
            if(node.right!=null){
                que.offer(node.right);
                nxtEnd=node.right;
            }
            //
            if(node==curEnd){
                max=Math.max(max, curNodeNum);
                curEnd=nxtEnd;
                curNodeNum=0;
            }
        }
        //
        return max;
    }
}
