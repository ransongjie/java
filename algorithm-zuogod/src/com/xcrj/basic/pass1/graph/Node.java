package com.xcrj.basic.pass1.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int val;//值
    List<Edge> edges;//邻接边
    List<Node> nodes;//邻接点
    int in;//入度
    int out;//出度
    public Node(){
        this.edges=new ArrayList<>();
        this.nodes=new ArrayList<>();
    }
    public Node(int val){
        this.val=val;
        this.edges=new ArrayList<>();
        this.nodes=new ArrayList<>();
    }
}
