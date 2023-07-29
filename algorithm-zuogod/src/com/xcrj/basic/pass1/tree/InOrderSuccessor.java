package com.xcrj.basic.pass1.tree;
//中序遍历后继结点
public class InOrderSuccessor {
    public static void main(String[] args) {
        NodeP n1=new NodeP(1);
        NodeP n2=new NodeP(2);
        NodeP n3=new NodeP(3);
        NodeP n4=new NodeP(4);
        NodeP n5=new NodeP(5);
        NodeP n6=new NodeP(6);
        NodeP n7=new NodeP(7);
        NodeP n8=new NodeP(8);
        n1.left=n2;n1.right=n3;
        n2.left=n4;n2.right=n5;
        n5.left=n6;
        n6.left=n7;
        n7.left=n8;
        n1.parent=n1;//
        n2.parent=n1;n3.parent=n1;
        n4.parent=n2;n5.parent=n2;
        n6.parent=n5;n7.parent=n6;n8.parent=n7;
        NodeP successor=getSuccesor(n4);
        System.out.println(successor.val);
        
    }

    public static NodeP getSuccesor(NodeP x) {
        //左根右 我是根 右（左根右）
        //右孩子不空，返回右孩子的最左孩子
        if(x.right!=null){
            NodeP n=x.right;
            while(n.left!=null){
                n=n.left;
            }
            return n;
        }
        //左根右，（左根右）根右
        //我是父亲的左孩子，返回父亲，否则一直往上找父亲
        else{
            NodeP p=x.parent;
            while(p.left!=x){
                x=p;
                p=x.parent;
            }
            return p;
        }
    }
}
