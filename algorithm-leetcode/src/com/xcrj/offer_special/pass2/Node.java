package com.xcrj.offer_special.pass2;

public class Node {
    int val;
    Node next;
    Node prev;
    Node child;

    public Node(Node next, Node prev, Node child) {
        this.next = next;
        this.prev = prev;
        this.child = child;
    }

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }
}
