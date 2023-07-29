package com.xcrj.basic.pass1.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//并查集实现 并 union 查 aSameSet 集 同根则在同一个集合中
public class MSTKruskal2 {
    static Map<Node,Node> ancestorMap=new HashMap<>();
    static Map<Node,Integer> rankMap=new HashMap<>();

    public static void initSet(Collection<Node> nodes) {
        ancestorMap.clear();
        rankMap.clear();
        for(Node n:nodes){
            ancestorMap.put(n, n);
            rankMap.put(n, 1);
        }
    }

    public static boolean aSameSet(Node a,Node b) {
        return findRoot(a)==findRoot(b);
    }

    //寻找过程中的所有后代的根都会被更新
    private static Node findRoot(Node n) {
        Node ancestor=ancestorMap.get(n);
        if(ancestor!=n){//
            ancestor=findRoot(ancestor);
        }
        ancestorMap.put(n, ancestor);
        return ancestor;
    }

    public static void union(Node a,Node b) {
        if(a==null||b==null) return;
        Node roota=findRoot(a);
        Node rootb=findRoot(b);
        if(roota!=rootb){
            int ranka=rankMap.get(roota);
            int rankb=rankMap.get(rootb);
            if(ranka>rankb){
                ancestorMap.put(rootb, roota);
                rankMap.put(roota, ranka+rankb);
            }else{
                ancestorMap.put(roota, rootb);
                rankMap.put(rootb, ranka+rankb);
            }
        }
    }

    public static void main(String[] args) {
        int[][] inss = {
                { 1, 2, 6 },
                { 1, 3, 1 },
                { 1, 4, 5 },
                { 2, 3, 5 },
                { 3, 4, 5 },
                { 2, 5, 3 },
                { 3, 5, 6 },
                { 3, 6, 4 },
                { 5, 6, 6 },
                { 4, 6, 2 },
                { 3, 4, 5 },
                { 2, 1, 6 },
                { 3, 1, 1 },
                { 4, 1, 5 },
                { 3, 2, 5 },
                { 4, 3, 5 },
                { 5, 2, 3 },
                { 5, 3, 6 },
                { 6, 3, 4 },
                { 6, 5, 6 },
                { 6, 4, 2 },
                { 4, 3, 5 },
        };

        Graph g=Convert.convert(inss);
        Set<Edge>set=kruskal(g);
        for(Edge e:set){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
    }
    
    public static Set<Edge> kruskal(Graph g) {
        Set<Edge> rset=new HashSet<>();
        //
        initSet(g.nodes.values());
        //
        PriorityQueue<Edge> pque=new PriorityQueue<>((o1,o2)->o1.weight-o2.weight);
        for(Edge e:g.edges){
            pque.offer(e);
        }
        while(!pque.isEmpty()){
            Edge e=pque.poll();
            if(!aSameSet(e.from, e.to)){
                rset.add(e);
                union(e.from, e.to);
            }
        }
        //
        return rset;
    }
}
