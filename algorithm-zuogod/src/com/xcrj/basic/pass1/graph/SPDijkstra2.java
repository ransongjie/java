package com.xcrj.basic.pass1.graph;

import java.util.HashMap;
import java.util.Map;

//改进堆 自定义堆 优化dijkstra
public class SPDijkstra2 {
    public static void main(String[] args) {
        int[][] inss = {
                { 0, 1, 4 },
                { 0, 2, 6 },
                { 0, 3, 6 },
                { 3, 2, 2 },
                { 1, 2, 1 },
                { 1, 4, 7 },
                { 3, 5, 5 },
                { 2, 5, 4 },
                { 5, 4, 1 },
                { 5, 6, 8 },
                { 4, 6, 6 },
                { 2, 4, 6 }
        };

        Graph g = Convert.convert(inss);
        for (Edge e : g.edges) {
            System.out.println(e.from.val + ", " + e.to.val + ", " + e.weight);
        }

        Map<Node, Integer> distanceMap = dijkstra(g);
        distanceMap.forEach((k, v) -> System.out.println(0 + ", " + k.val + "=" + v));
    }
    public static Map<Node, Integer> dijkstra(Graph g) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        //
        Node s = g.nodes.get(1);
        NodeHeap nheap = new NodeHeap(g.nodes.size());
        nheap.addOrUpdateOrIgnore(s, 0);
        //
        while (!nheap.isEmpty()) {
            NodeRecord a = nheap.pop();
            for (Edge e : a.node.edges) {
                nheap.addOrUpdateOrIgnore(e.to, a.distance + e.weight);
            }
            distanceMap.put(a.node, a.distance);
        }
        //
        return distanceMap;
    }

    static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class NodeHeap {
        Node[] nodes;// heap
        Map<Node, Integer> nodeIdxMap;
        Map<Node, Integer> nodeDistanceMap;
        int size;

        public NodeHeap(int n) {
            size = 0;
            nodes = new Node[n];
            nodeIdxMap = new HashMap<>();
            nodeDistanceMap = new HashMap<>();
        }

        // 是否进过堆
        public boolean entered(Node n) {
            return nodeIdxMap.containsKey(n);
        }

        // 是否在堆中
        public boolean inHeap(Node n) {
            return nodeIdxMap.containsKey(n) && nodeIdxMap.get(n) != -1;
        }

        // 建立堆 更新堆
        public void addOrUpdateOrIgnore(Node n, int distance) {
            if (!entered(n)) {// add
                nodes[size] = n;
                nodeIdxMap.put(n, size);
                nodeDistanceMap.put(n, distance);
                heapInsert(size++);
                return;
            }
            if (inHeap(n)) {// update
                int idx = nodeIdxMap.get(n);
                if (nodeDistanceMap.get(n) <= distance)
                    return;
                nodeDistanceMap.put(n, distance);// 上一行 新行
                heapInsert(idx);// idx往下 已经是小根堆 更小应该往上调整
                return;
            }
            // ignore
        }

        // 把堆顶元素弹出，对堆顶元素堆化即可
        public NodeRecord pop() {
            NodeRecord nr = new NodeRecord(nodes[0], nodeDistanceMap.get(nodes[0]));
            swap(0, size-1);
            nodeIdxMap.put(nodes[size-1],-1);
            nodeDistanceMap.remove(nodes[size-1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nr;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 往上 小跟堆
        private void heapInsert(int idx) {
            while (nodeDistanceMap.get(nodes[idx]) < nodeDistanceMap.get(nodes[(idx - 1) / 2])) {
                swap(idx, (idx - 1) / 2);
                idx = (idx - 1) / 2;
            }
        }

        // 往下 从idx开始到size 堆化
        private void heapify(int idx, int size) {
            int left = idx * 2 + 1;
            int right = idx * 2 + 2;
            while (left < size) {
                int less = 0;
                if (right >= size || nodeDistanceMap.get(nodes[left]) < nodeDistanceMap.get(nodes[right]))
                    less = left;
                else
                    less = right;
                if (nodeDistanceMap.get(nodes[idx]) > nodeDistanceMap.get(nodes[less]))
                    swap(less, idx);
                else break;// 没有产生交换，后续也不会再产生交换
                idx = less;
                left = idx * 2 + 1;
                right = idx * 2 + 2;
            }
        }

        //
        private void swap(int idx1, int idx2) {
            nodeIdxMap.put(nodes[idx1], idx2);
            nodeIdxMap.put(nodes[idx2], idx1);
            Node tmp = nodes[idx1];
            nodes[idx1] = nodes[idx2];
            nodes[idx2] = tmp;
        }
    }
}
