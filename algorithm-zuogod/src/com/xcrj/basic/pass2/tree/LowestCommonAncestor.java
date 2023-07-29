package com.xcrj.basic.pass2.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//最低公共祖先结点
public class LowestCommonAncestor {
    public static void main(String[] args) {
        //o1和o2在同一分支，o1在上
        // Node node1=new Node(1);
        // Node node2=new Node(2);
        // Node node3=new Node(3);
        // Node node4=new Node(4);
        // Node node5=new Node(5);
        // node1.left=node2;node2.left=node3;node3.left=node4;node4.left=node5;
        // Node o1=node3;Node o2=node5;
        // Util.printTree(node1);
        // //3
        // System.out.println(lca(node1, o1, o2).val);
        // //3
        // System.out.println(lca2(node1, o1, o2).val);

        //从node2往右子树是o1，左子树是o2
        // Node node1=new Node(1);
        // Node node2=new Node(2);
        // Node node3=new Node(3);
        // Node node4=new Node(4);
        // Node node5=new Node(5);
        // Node node6=new Node(6);
        // node1.left=node2;node1.right=node3;
        // node2.left=node4;node2.right=node5;
        // node4.left=node6;
        // Node o1=node5;Node o2=node6;
        // Util.printTree(node1);
        // // 2
        // System.out.println(lca(node1, o1, o2).val);
        // // 2
        // System.out.println(lca2(node1, o1, o2).val);
    }

    public static Node lca(Node n,Node o1,Node o2) {
        //构建每个结点到parent的路径
        Map<Node,Node> nodeParent=new HashMap<>();
        nodeParent.put(n,n);
        handleParentMap(n, nodeParent);

        //构建o1到parent到根的路径
        Set<Node> set=new HashSet<>();
        Node p1=o1;
        while(p1!=n){
            set.add(p1);
            p1=nodeParent.get(p1);
        }
        set.add(n);

        //
        Node p2=o2;
        while(!set.contains(p2)){
            p2=nodeParent.get(p2);
        }

        return p2;
    }

    private static void handleParentMap(Node n,Map<Node,Node> nodeParent){
        if(n==null) return;
        if(n.left!=null) nodeParent.put(n.left,n);
        if(n.right!=null) nodeParent.put(n.right, n);
        handleParentMap(n.left, nodeParent);
        handleParentMap(n.right, nodeParent);
    }

    public static Node lca2(Node n,Node o1,Node o2) {
        //o1在o2的子树上 或 o2在o1的子树上
        if(sonTree(o1, o2)){
            return o1;
        }
        if(sonTree(o2, o1)){
            return o2;
        }

        //o1和o2在某root的左右分支上
        return sonTree2(n, o1, o2);
    }

    //o是否在n的子树上
    private static boolean sonTree(Node n,Node o){
        if(n==null||o==null) return false;
        if(n==o) return true;
        return sonTree(n.left, o)||sonTree(n.right, o);
    }

    //从n往左子树找到了o1，往右子树找到了o2
    private static Node sonTree2(Node n,Node o1,Node o2){
        if(n==null||n==o1||n==o2) return n;
        //从n往左子树找到了o1，往右子树找到了o2
        Node left=sonTree2(n.left, o1, o2);
        Node right=sonTree2(n.right, o1, o2);
        if(left!=null&&right!=null){
            return n;
        }
        return left==null?right:left;
    }
}
