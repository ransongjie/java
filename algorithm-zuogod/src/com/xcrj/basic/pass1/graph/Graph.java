package com.xcrj.basic.pass1.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    Map<Integer,Node> nodes;//点们
    Set<Edge> edges;//边们
    public Graph() {
        this.nodes=new HashMap<>();
        this.edges=new HashSet<>();
    }
}
