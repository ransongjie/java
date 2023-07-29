package com.xcrj.basic.pass1.graph;

public class Edge {
    Node from;
    Node to;
    int weight;
    public Edge(){}
    public Edge(Node from,Node to,int weight){
        this.from=from;
        this.to=to;
        this.weight=weight;
    }
}
