package com.xcrj.interview150.graph;

import java.util.*;

/**
 * https://leetcode.cn/problems/clone-graph
 * 克隆图
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 */
public class Solution03 {
    /**
     * 广度遍历 hash<原结点,克隆结点>
     *
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        //存储 原结点 对应的 克隆结点
        Map<Node, Node> visited = new HashMap<>();
        //入队列
        Queue<Node> que = new LinkedList<>();
        que.offer(node);
        //克隆
        visited.put(node, new Node(node.val, new ArrayList<>()));

        //广度优先遍历
        Node n;
        while (!que.isEmpty()) {
            n = que.poll();
            //广度优先
            for (Node neighbor : n.neighbors) {
                //没访问过，则访问，则克隆
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    //入队
                    que.add(neighbor);
                }
                // n的克隆结点 列表 add n的邻居克隆结点
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    Node(int v, List<Node> ns) {
        val = v;
        neighbors = ns;
    }
}
