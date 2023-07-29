package com.xcrj.basic.pass2.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int val;
    List<Node> adjns;
    List<Edge> adjes;
    int in;
    int out;

    public Node() {}
    public Node(int val) {
        this.val=val;
        this.adjns=new ArrayList<>();
        this.adjes=new ArrayList<>();
    }
}
