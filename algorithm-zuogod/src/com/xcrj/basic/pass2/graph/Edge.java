package com.xcrj.basic.pass2.graph;

public class Edge {
    int weight;
    Node from;
    Node to;
    public Edge(){}
    public Edge(Node from,Node to,int weight){
        this.from=from;
        this.to=to;
        this.weight=weight;
    }
}
