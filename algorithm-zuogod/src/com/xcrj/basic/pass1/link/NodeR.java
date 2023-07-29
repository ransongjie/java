package com.xcrj.basic.pass1.link;

public class NodeR {
    int val;
    NodeR next;
    NodeR rand;
    public NodeR() {
        
    }
    public NodeR(int val) {
        this.val=val;
    }
    public NodeR(int val,NodeR next) {
        this.val=val;
        this.next=next;
    }
    public NodeR(NodeR rand) {
        this.rand=rand;
    }
}
