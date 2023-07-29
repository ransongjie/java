package com.xcrj.basic.pass1.link;

public class Node {
    int val;
    Node next;
    public Node() {
        
    }
    public Node(int val) {
        this.val=val;
    }
    public Node(int val,Node next) {
        this.val=val;
        this.next=next;
    }
}
