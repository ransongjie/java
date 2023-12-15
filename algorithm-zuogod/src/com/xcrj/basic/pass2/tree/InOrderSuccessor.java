package com.xcrj.basic.pass2.tree;

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
        NodeP n9=new NodeP(9);
        NodeP n10=new NodeP(10);
        NodeP n11=new NodeP(11);
        n1.left=n2;n1.right=n3;
        n2.left=n4;n2.right=n5;
        n5.left=n6;
        n6.left=n7;
        n7.left=n8;
        n3.left=n9;n9.right=n10;n10.right=n11;
        n1.parent=n1;
        n2.parent=n1;n3.parent=n1;
        n4.parent=n2;n5.parent=n2;
        n6.parent=n5;n7.parent=n6;n8.parent=n7;
        n11.parent=n10;n10.parent=n9;n9.parent=n3;

        //x无后继
        //null
        System.out.println(ios(n1,n3));

        //x存在右子树
        //5
        System.out.println(ios(n1, n2).val);

        //x在n的左子树上
        //3
        System.out.println(ios(n1,n11).val);
        
    }

    /**
     * NodeP 有parent指针
     * @param n root
     * @param x need
     */
    public static NodeP ios(NodeP n,NodeP x) {
        // 有右孩子返回右孩子
        if(x.right!=null) return x.right;
        // 无右孩子，找左孩子的父亲
        NodeP p=x;
        while(p!=n){
            if (p.parent.left==p){
                return p.parent;
            }
            p=p.parent;
        }
        return null;
    }
}
