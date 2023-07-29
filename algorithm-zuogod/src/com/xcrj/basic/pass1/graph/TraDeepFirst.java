package com.xcrj.basic.pass1.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TraDeepFirst {
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
            {6,4,0},
            {6,7,0}
        };
        Graph g=Convert.convert(inss);
        for(Edge e:g.edges){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
        dfs(g);
    }

    public static void dfs(Graph g) {
        Stack<Node> stack=new Stack<>();
        Set<Node> selected=new HashSet<>();
        stack.push(g.nodes.get(1));
        System.out.println(1);
        while(!stack.isEmpty()){
            Node a=stack.pop();
            for(Node b:a.nodes){
                if(selected.contains(b)) continue;
                stack.push(a);//
                stack.push(b);
                selected.add(b);
                System.out.println(b.val);
                break;//
            }
        }
    }
}
