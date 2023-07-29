package com.xcrj.basic.pass1.tree;

public class NodeP {
    int val;
    NodeP left;
    NodeP right;
    NodeP parent;
    public NodeP(){}
    public NodeP(int val){
        this.val=val;
        this.left=null;
        this.right=null;
        this.parent=null;
    }
}
