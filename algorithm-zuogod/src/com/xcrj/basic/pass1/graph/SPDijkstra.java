package com.xcrj.basic.pass1.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//最短路径
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
        Graph g=Convert.convert(inss);
        for (Edge e : g.edges) {
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
        Map<Node,Integer> distanceMap=dijkstra(g.nodes.get(0));
        distanceMap.forEach((k,v)->System.out.println(0+", "+k.val+"="+v));
    }

    //s出发点 到每一个点的最短距离
    public static Map<Node,Integer> dijkstra(Node s) {
        Map<Node,Integer> distanceMap=new HashMap<>();
        Set<Node> selectedSet=new HashSet<>();
        distanceMap.put(s, 0);//源点到源点距离
        Node a=getMinDistanceAndUnselectedNode(distanceMap,selectedSet);//选点 最小边
        //
        while(a!=null){//
            int adistance=distanceMap.get(a);
            selectedSet.add(a);
            for(Edge e:a.edges){
                Node b=e.to;
                int bdistanceOld=distanceMap.getOrDefault(b, Integer.MAX_VALUE);
                int abdistance=e.weight;
                int bdistanceNew=adistance+abdistance;
                if(bdistanceNew<bdistanceOld){//选边 新旧对比
                    distanceMap.put(b, bdistanceNew);
                }
            }
            a=getMinDistanceAndUnselectedNode(distanceMap,selectedSet);//
        }
        //
        return distanceMap;
    }

    /**
     * @param distanceMap
     * @param selectedSet
     * @return null
     */
    public static Node getMinDistanceAndUnselectedNode(Map<Node,Integer> distanceMap,Set<Node> selectedSet) {
        //选择
        Node minNode=null;
        int minDistance=Integer.MAX_VALUE;
        for(Entry<Node,Integer> entry:distanceMap.entrySet()){
            Node n=entry.getKey();
            int distance=entry.getValue();
            if(!selectedSet.contains(n)&&distance<minDistance){
                minDistance=distance;
                minNode=n;
            }
        }
        //
        return minNode;
    }
}
