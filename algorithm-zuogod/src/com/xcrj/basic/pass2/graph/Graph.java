package com.xcrj.basic.pass2.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    Map<Integer,Node> nodes;
    Set<Edge> edges;
    public Graph(){
        this.nodes=new HashMap<>();
        this.edges=new HashSet<>();
    }
}
