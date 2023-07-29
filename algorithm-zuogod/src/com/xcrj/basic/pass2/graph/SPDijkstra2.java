package com.xcrj.basic.pass2.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 最短路径
 * 自建堆
 * dijkstra在原本已经加入系统堆的结点的距离会再变化，系统堆不能满足需求
 */
public class SPDijkstra2 {
    public static void main(String[] args) {
        int[][] inss={
            {0,1,4},
            {0,2,6},
            {0,3,6},
            {3,2,2},
            {1,2,1},
            {1,4,7},
            {3,5,5},
            {2,5,4},
            {5,4,1},
            {5,6,8},
            {4,6,6},
            {2,4,6}
        };
        Graph g=Convert.toGraph(inss);
        for (Edge e : g.edges) {
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }

        Map<Node,Integer> distanceMap=dijkstra(g.nodes.size(),g.nodes.get(0));
        distanceMap.forEach((k,v)->System.out.println(0+"->"+k.val+"="+v));
    }

    public static Map<Node,Integer> dijkstra(int len,Node s) {
        Map<Node,Integer> distanceMap=new HashMap<>();

        NodeHeap nh=new NodeHeap(len);
        nh.addOrUpdateOrIgnore(s, 0);//选点
        while(!nh.isEmpty()){
            NodeRecord a=nh.pop();
            distanceMap.put(a.node, a.distance);
            for(Edge e:a.node.adjes){
                //oldDistance: soruce->a
                //oldDistance: soruce->to->a
                nh.addOrUpdateOrIgnore(e.to, e.weight+a.distance);//选边
            }
        }
        
        return distanceMap;
    }

    static class NodeRecord{
        Node node;
        int distance;
        public NodeRecord(Node node,int distance) {
            this.node=node;
            this.distance=distance;
        }
    }

    static class NodeHeap{
        Node[] nodes;//heap array
        int size;//下一个add的位置
        Map<Node,Integer> nodeIdx;
        Map<Node,Integer> nodeDistance;

        public NodeHeap(int len) {
            this.nodes=new Node[len];
            this.size=0;
            this.nodeIdx=new HashMap<>();
            this.nodeDistance=new HashMap<>();
        }

        public boolean isEmpty() {
            return size==0;
        }

        //更新时，distance 比原来更小，往上重新建小根堆
        public void addOrUpdateOrIgnore(Node node,int distance) {
            //add
            if(!entered(node)){
                nodes[size]=node;
                nodeIdx.put(node, size);
                nodeDistance.put(node, distance);
                size++;
                return;
            }

            //update
            if(inheap(node)){
                if(distance>=nodeDistance.get(node)) return;
                nodeDistance.put(node, distance);
                heapInsert(nodeIdx.get(node));//（source—>node>to）distance 比原来更小，往上重新建小根堆
            }

            //ignore
        }

        //需要重建堆
        public NodeRecord pop() {
            NodeRecord nr=new NodeRecord(nodes[0], nodeDistance.get(nodes[0]));
            swap(0, --size);
            nodeIdx.put(nodes[size], -1);//
            nodeDistance.remove(nodes[size]);
            nodes[size]=null;
            heapfy();
            return nr;
        }

        //在堆中 size内
        private boolean inheap(Node node) {
            return nodeIdx.containsKey(node)&&nodeIdx.get(node)!=-1;
        }

        //进入过堆 nodeIdx.put(nodes[size], -1);也算进入过堆
        private boolean entered(Node node){
            return nodeIdx.containsKey(node);
        }

        private void heapInsert(int idx) {
            //小根堆，子比父小则交换
            while(nodeDistance.get(nodes[idx])<nodeDistance.get(nodes[(idx-1)/2])){
                swap(idx, (idx-1)/2);
                idx=(idx-1)/2;
            }
        }

        //小根堆，子子比较，父子比较
        private void heapfy(){
            int parent=0;
            int left=parent*2+1;
            int right=parent*2+2;
            while(left<size){
                int less=0;
                if(right>=size||nodeDistance.get(nodes[left])<nodeDistance.get(nodes[right])){//
                    less=left;
                }else{
                    less=right;
                }
                //已经满足，break，不用继续比较
                if(nodeDistance.get(nodes[parent])<=nodeDistance.get(nodes[less])) break;
                swap(parent, less);
                parent=less;
                left=parent*2+1;
                right=parent*2+2;
            }
        }

        private void swap(int i,int j) {
            nodeIdx.put(nodes[i], j);
            nodeIdx.put(nodes[j], i);
            Node temp=nodes[i];
            nodes[i]=nodes[j];
            nodes[j]=temp;
        }
    }
}
