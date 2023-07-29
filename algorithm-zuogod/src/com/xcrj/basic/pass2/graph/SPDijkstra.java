package com.xcrj.basic.pass2.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最短路径
 */
public class SPDijkstra {
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

        Map<Node,Integer> distanceMap=dijkstra(g.nodes.get(0));
        /**
         * 0->1=10
         * 1->2=3
         */
        distanceMap.forEach((k,v)->System.out.println(0+"->"+k.val+"="+v));
    }

    public static Map<Node,Integer> dijkstra(Node s) {
        Map<Node,Integer> distanceMap=new HashMap<>();
        Set<Node> selectedSet=new HashSet<>();
        distanceMap.put(s,0);//源点到源点的距离为0
        Node a=getMinDistanceAndUnselectedNode(distanceMap, selectedSet);//选点
        while(a!=null){
            selectedSet.add(a);

            int aDistance=distanceMap.get(a);
            for(Edge e:a.adjes){//选边 直达与中转对比
                int oldDistance=distanceMap.getOrDefault(e.to, Integer.MAX_VALUE);
                int newDistacne=e.weight+aDistance;
                if(oldDistance>newDistacne){
                    distanceMap.put(e.to, newDistacne);
                }
            }

            a=getMinDistanceAndUnselectedNode(distanceMap, selectedSet);//选点
        }
        return distanceMap;
    }

    //选择排序，一趟获取最小值
    private static Node getMinDistanceAndUnselectedNode(Map<Node,Integer> distanceMap,Set<Node> selectedSet) {
        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;
        for (Map.Entry<Node,Integer> entry : distanceMap.entrySet()) {
            if(!selectedSet.contains(entry.getKey())&&minDistance>entry.getValue()){
                minNode=entry.getKey();
                minDistance=entry.getValue();
            }
        }
        
        return minNode;
    }
}
