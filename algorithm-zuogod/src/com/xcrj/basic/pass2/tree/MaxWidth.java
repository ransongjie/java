package com.xcrj.basic.pass2.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 最大宽度
 */
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

    public static int maxWidth(Node n) {
        if(n==null) return 0;
        Queue<Node> que=new LinkedList<>();
        Map<Node,Integer> nodeLevel=new HashMap<>();
        Node p=n;
        int level=1;
        que.offer(p);
        nodeLevel.put(p,level);
        while(!que.isEmpty()){
            p=que.poll();
            level=nodeLevel.get(p);//层序遍历 最后一个出队的结点有最大层数
            if(p.left!=null){
                que.offer(p.left);
                nodeLevel.put(p.left, level+1);
            }
            if(p.right!=null){
                que.offer(p.right);
                nodeLevel.put(p.right, level+1);
            }
        }

        int maxWidth=0;
        for (int i = 1; i <= level; i++) {
            int width=0;
            for (int lev: nodeLevel.values()) {
                if(lev==i) width++;
            }
            if(maxWidth<width) maxWidth=width;
        }

        return maxWidth;
    }

    public static int maxWidth2(Node n) {
        if(n==null) return 0;
        int maxWidth=0;
        Queue<Node> que=new LinkedList<>();
        Map<Node,Integer> nodeLevel=new HashMap<>();
        Node p=n;
        int level=1;
        que.offer(p);
        nodeLevel.put(p,level);
        int curLevel=1;
        int width=0;
        while(!que.isEmpty()){
            p=que.poll();
            level=nodeLevel.get(p);//层序遍历 最后一个出队的结点有最大层数

            if(curLevel==level){
                width++;
            }else{
                maxWidth=Math.max(maxWidth, width);
                curLevel=level;
                width=1;
            }

            if(p.left!=null){
                que.offer(p.left);
                nodeLevel.put(p.left, level+1);
            }
            if(p.right!=null){
                que.offer(p.right);
                nodeLevel.put(p.right, level+1);
            }
        }

        return maxWidth;
    }

    /**
     * 广度优先遍历 双层指针 curEnd nxtEnd
     */
    public static int maxWidth3(Node n) {
        if(n==null) return 0;
        
        Node curLevelLastNode=n;
        Node nxtLevelLastNode=null;
        int curLevelNum=0;
        int maxLevelNum=0;
        
        Queue<Node> que=new LinkedList<>();
        que.offer(n);
        while(!que.isEmpty()){
            Node p=que.poll();
            if(p.left!=null){
                que.offer(p.left);
                nxtLevelLastNode=p.left;
            }
            if(p.right!=null){
                que.offer(p.right);
                nxtLevelLastNode=p.right;
            }

            curLevelNum++;
            if(p==curLevelLastNode){//来到了当前层的最后一个结点
                maxLevelNum=Math.max(maxLevelNum, curLevelNum);
                curLevelLastNode=nxtLevelLastNode;
                nxtLevelLastNode=null;
                curLevelNum=0;
            }
        }

        return maxLevelNum;
    }
}
