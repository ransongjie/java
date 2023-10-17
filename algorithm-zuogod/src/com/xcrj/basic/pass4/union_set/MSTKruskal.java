package com.xcrj.basic.pass4.union_set;

import java.util.*;

/**
 * 最小生成树
 * 避圈法，并查集
 */
public class MSTKruskal {
    public static Set<Edge> kruskal(Graph g) {
        Set<Edge> ans = new HashSet<>();

        //初始化并查集
        UFind.init(g.nodes.values());

        //排序边
        PriorityQueue<Edge> pque = new PriorityQueue<>((a, b) -> a.weight > b.weight ? 1 : a.weight == b.weight ? 0 : -1);
        for (Edge e : g.edges) {
            pque.offer(e);
        }

        //选边，避开圈
        while (!pque.isEmpty()) {
            Edge e = pque.poll();
            if (UFind.isSameSet(e.from, e.to)) continue;
            UFind.union(e.from, e.to);
            ans.add(e);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] inss = {
                {1, 2, 6},
                {1, 3, 1},
                {1, 4, 5},
                {2, 3, 5},
                {3, 4, 5},
                {2, 5, 3},
                {3, 5, 6},
                {3, 6, 4},
                {5, 6, 6},
                {4, 6, 2},
                {3, 4, 5},
                {2, 1, 6},
                {3, 1, 1},
                {4, 1, 5},
                {3, 2, 5},
                {4, 3, 5},
                {5, 2, 3},
                {5, 3, 6},
                {6, 3, 4},
                {6, 5, 6},
                {6, 4, 2},
                {4, 3, 5},
        };

        Graph g = Convert.toGraph(inss);
        Set<Edge> set = kruskal(g);
        for (Edge e : set) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }
    }
}

class UFind {
    static Map<Node, Node> nodeFather = new HashMap<>();
    static Map<Node, Integer> rootNum = new HashMap<>();

    /**
     * 初始化
     *
     * @param nodes
     */
    static void init(Collection<Node> nodes) {
        for (Node n : nodes) {
            nodeFather.put(n, n);
            rootNum.put(n, 1);
        }
    }

    /**
     * 两结点是否处于同一个集合
     *
     * @param a
     * @param b
     * @return
     */
    static boolean isSameSet(Node a, Node b) {
        return findRoot(a) == findRoot(b);
    }

    /**
     * 合并两个结点所在集合
     *
     * @param a
     * @param b
     */
    static void union(Node a, Node b) {
        if (a == null || b == null) return;
        Node aRoot = findRoot(a);
        Node bRoot = findRoot(b);
        Node bigRoot = rootNum.get(aRoot) > rootNum.get(bRoot) ? aRoot : bRoot;
        Node smlRoot = bigRoot == aRoot ? bRoot : aRoot;
        nodeFather.put(smlRoot, bigRoot);
        rootNum.put(bigRoot, rootNum.get(smlRoot) + rootNum.get(bigRoot));
        rootNum.remove(smlRoot);
    }

    /**
     * 寻找结点的根节点
     *
     * @param n
     * @return
     */
    private static Node findRoot(Node n) {
        if (n == null) return null;
        Stack<Node> stack = new Stack<>();
        stack.push(n);
        Node father = nodeFather.get(n);

        while (n != father) {
            n = father;
            father = findRoot(n);
            stack.push(n);
        }

        while (!stack.isEmpty()) {
            nodeFather.put(stack.pop(), n);
        }

        return n;
    }
}

class Convert {
    static Graph toGraph(int[][] inss) {
        Graph graph = new Graph();
        for (int[] ins : inss) {
            int a = ins[0];
            int b = ins[1];
            int w = ins[2];
            if (!graph.nodes.containsKey(a)) {
                Node n = new Node(a);
                graph.nodes.put(a, n);
            }
            if (!graph.nodes.containsKey(b)) {
                Node n = new Node(b);
                graph.nodes.put(b, n);
            }

            Node from = graph.nodes.get(a);
            Node to = graph.nodes.get(b);
            Edge e = new Edge(from, to, w);
            from.adjns.add(to);
            from.adjes.add(e);
            from.out++;
            to.in++;
            graph.edges.add(e);
        }
        return graph;
    }
}

class Graph {
    Map<Integer, Node> nodes;
    Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}

class Edge {
    int weight;
    Node from;
    Node to;

    Edge() {
    }

    Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Node {
    int val;
    List<Node> adjns;
    List<Edge> adjes;
    int in;
    int out;

    Node() {
    }

    Node(int val) {
        this.val = val;
        this.adjns = new ArrayList<>();
        this.adjes = new ArrayList<>();
    }
}
