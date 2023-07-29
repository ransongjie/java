package com.xcrj.basic.pass2.graph;

import java.util.LinkedList;
import java.util.Queue;

public class TopoSort {
    public static void main(String[] args) {
        int[][] inss={//有向图
            {1,2,0},
            {1,8,0},
            {2,3,0},
            {2,5,0},
            {3,4,0},
            {5,4,0},
            {8,6,0},
            {8,9,0},
            {9,7,0},
            {7,4,0},
            {6,4,0},
            {6,7,0}
        };
        Graph g=Convert.toGraph(inss);
        for(Edge e:g.edges){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
        //
        topo(g);
    }

    public static void topo(Graph g) {
        Queue<Node> que=new LinkedList<>();
        for(Node n:g.nodes.values()){
            if(n.in==0){
                que.offer(n);
            }
        }

        while(!que.isEmpty()){
            Node a=que.poll();
            System.out.println(a.val);
            //连接点入度--后为0，则加入队列
            for(Node b:a.adjns){
                b.in--;
                if(b.in==0){
                    que.offer(b);
                }
            }
        }
    }
}
