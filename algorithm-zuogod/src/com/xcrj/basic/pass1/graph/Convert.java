package com.xcrj.basic.pass1.graph;

//将数组结构输入 转 熟悉图结构
public class Convert {
    public static void main(String[] args) {
        //{fromNode,endNode,weight}
        int[][] inss={
            {0,1,5},
            {1,2,3},
            {0,2,7}
        };
        Graph g=convert(inss);
        for(Node n:g.nodes.values()){
            System.out.println(n.val+": "); 
            n.nodes.forEach(o->System.out.print(o.val+", "));
            System.out.println();
            n.edges.forEach(o->System.out.print(o.weight+", "));
            System.out.println();
            System.out.println(n.in);
            System.out.println(n.out);
        }
        System.out.println("==============");
        for(Edge e:g.edges){
            System.out.println(e.from.val+", "+e.to.val+", "+e.weight);
        }
    }

    public static Graph convert(int[][] inss) {
        Graph g=new Graph();
        //graph.nodes 图点们
        //graph.edges 图边们
        for(int[] ins:inss){
            int a=ins[0];int b=ins[1];int w=ins[2];
            //
            if(!g.nodes.containsKey(a)){
                Node na=new Node(a);
                g.nodes.put(a, na);
            }
            if(!g.nodes.containsKey(b)){
                Node nb=new Node(b);
                g.nodes.put(b, nb);
            }
            //
            Node na=g.nodes.get(a);
            Node nb=g.nodes.get(b);
            Edge ab=new Edge(na,nb,w);
            na.edges.add(ab);//从ab边到nb点
            na.nodes.add(nb);
            na.out++;
            nb.in++;
            //
            g.edges.add(ab);
        }
        //
        return g;
    }
}
