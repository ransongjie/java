package com.xcrj.basic.pass1.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最低公共祖先
 * o1或o2在另一个的子树上
 * o1和o2在o的左右子树上
 */
public class LowestCommonAncestor {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        node1.left=node2;node2.left=node3;node3.left=node4;node4.left=node5;
        Node o1=node3;Node o2=node5;
        Node root=node1;
        // Util.printTree(root);

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
        // Node root=node1;
        // Util.printTree(root);

        // Node o=ancestor(root, o1, o2);
        Node o=ancestor2(root, o1, o2);
        // Node o=ancestor3(root, o1, o2);
        System.out.println(o.val);
    }

    public static Node ancestor(Node root,Node o1,Node o2) {
        if(o1==null||o2==null) return null;
        //o1或o2在另一个的子树上
        if(sonTree(o1,o2)){
            return o1;
        }
        if(sonTree(o2, o1)){
            return o1;
        }
        //o1和o2在o的左右子树上
        return sonTree2(root, o1, o2);
    }

    public static boolean sonTree(Node root,Node o) {
        if(root==null) return false;
        if(root==o) return true;
        boolean left=sonTree(root.left, o);
        boolean right=sonTree(root.right, o);
        return left||right;
    }

    public static Node sonTree2(Node root,Node o1,Node o2) {
        //找到一个就返回
        if(root==null||root==o1||root==o2){
            return root;
        }
        //
        Node left=sonTree2(root.left, o1, o2);
        Node right=sonTree2(root.right, o1, o2);
        //左右都不为空返回root
        if(left!=null&&right!=null){
            return root;
        }
        //需要将找到的right或left返回上一层
        return left==null?right:left;
    }

    public static Node ancestor2(Node root,Node o1,Node o2) {
        if(root==null) return null;
        Map<Node,Node> parentMap=new HashMap<>();
        parentMap.put(root,root);
        handleParentMap(root,parentMap);
        
        Set<Node> o1ParentSet=new HashSet<>();
        Node o=o1;
        while(o!=root){
            o1ParentSet.add(o);
            o=parentMap.get(o);
        }

        o=o2;
        while(!o1ParentSet.contains(o)){
            o=parentMap.get(o);
        }

        return o;
    }

    public static void handleParentMap(Node root,Map<Node,Node> parentMap) {
        if(root==null) return;
        if(root.left!=null)parentMap.put(root.left, root);
        if(root.right!=null)parentMap.put(root.right, root);
        handleParentMap(root.left, parentMap);
        handleParentMap(root.right, parentMap);
    }

    /**
     * 1. o1和o2在o的左右子树上
     * 2. o1或o2在o的某一个子树上
     * @param root
     * @param o1
     * @param o2
     * @return
     */
    public static Node ancestor3(Node root,Node o1,Node o2) {
        if(root==null||root==o1||root==o2){
            return root;//返回祖先
        }
        //o1和o2在o的左右子树上
        Node left=ancestor3(root.left, o1, o2);
        Node right=ancestor3(root.right, o1, o2);
        if(left!=null&&right!=null){
            return root;
        }

        //需要将找到的right或left返回上一层
        return left==null?right:left;
    }
}
