package com.xcrj.basic.pass2.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TraDeepFirst {
    public static void main(String[] args) {
        int[][] inss = { // 有向图
                { 1, 2, 0 },
                { 1, 8, 0 },
                { 2, 3, 0 },
                { 2, 5, 0 },
                { 3, 4, 0 },
                { 5, 4, 0 },
                { 8, 6, 0 },
                { 8, 9, 0 },
                { 6, 4, 0 },
                { 6, 7, 0 }
        };
        Graph g = Convert.toGraph(inss);
        for (Edge e : g.edges) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }
        dfs(g);
    }

    public static void dfs(Graph g) {
        Stack<Node> s = new Stack<>();
        Set<Node> visited = new HashSet<>();
        s.push(g.nodes.get(1));//入栈访问
        System.out.println(g.nodes.get(1).val);
        while (!s.isEmpty()) {
            Node a = s.pop();
            for (Node b : a.adjns) {
                if (visited.contains(b)) continue;
                s.push(a);//还有其它连接点
                s.push(b);//后加入的先访问
                visited.add(b);
                System.out.println(b.val);
                break;
            }
        }
    }
}
