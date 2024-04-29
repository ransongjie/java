package com.xcrj.binary_tree;

public class Node {
    int val;
    Node left;
    Node right;

    Node(int v) {
        val = v;
    }

    Node(int v, Node l, Node r) {
        val = v;
        left = l;
        right = r;
    }
}
