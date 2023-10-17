package com.xcrj.binary_tree;

public class BTreeNode {
    int val;
    BTreeNode left;
    BTreeNode right;

    BTreeNode(int v) {
        val = v;
    }

    BTreeNode(int v, BTreeNode l, BTreeNode r) {
        val = v;
        left = l;
        right = r;
    }
}
