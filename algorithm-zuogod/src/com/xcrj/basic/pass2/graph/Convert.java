package com.xcrj.basic.pass2.graph;

public class Convert {
    public static void main(String[] args) {
        //{fromNode,endNode,weight}
        int[][] inss={
            {0,1,5},
            {1,2,3},
            {0,2,7}
        };
        Graph graph=toGraph(inss);
        for(Node node:graph.nodes.values()){
            StringBuilder sb=new StringBuilder();
            for(Node adjn:node.adjns){
                sb.append(adjn.val).append(" ");
            }
            System.out.println(node.val+"->"+sb.toString()+", in="+node.in+", out="+node.out);
        }
        System.out.println("=================");
        for(Edge e:graph.edges){
            System.out.println(e.from.val+","+e.to.val+","+e.weight);
        }
    }

    public static Graph toGraph(int[][] inss) {
        Graph graph=new Graph();
        for(int[] ins:inss){
            int a=ins[0];int b=ins[1];int w=ins[2];
            if(!graph.nodes.containsKey(a)){
                Node n=new Node(a);
                graph.nodes.put(a,n);
            }
            if(!graph.nodes.containsKey(b)){
                Node n=new Node(b);
                graph.nodes.put(b, n);
            }

            Node from=graph.nodes.get(a);
            Node to=graph.nodes.get(b);
            Edge e=new Edge(from, to, w);
            from.adjns.add(to);
            from.adjes.add(e);
            from.out++;
            to.in++;
            graph.edges.add(e);
        }
        return graph;
    }
}
