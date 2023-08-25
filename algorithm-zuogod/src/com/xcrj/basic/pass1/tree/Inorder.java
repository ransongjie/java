package com.xcrj.basic.pass1.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//中序遍历
public class Inorder {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        //
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node4.right = null;
        node5.left = node5.right = null;
        node6.left = node6.right = null;
        node7.left = node7.right = null;
        //
        Node root = node1;
        //
//         inorder(root);
        inorder2(root);
//        inorder3(root);
    }

    //一直入栈左
    public static void inorder(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        Set<Integer> set = new HashSet<>();
        s.push(root);
        while (!s.isEmpty()) {
            Node n = s.peek();
            if (n.left != null && !set.contains(n.left.val)) {
                s.push(n.left);
                continue;
            }

            Node m = s.pop();
            System.out.println(m.val);
            set.add(m.val);
            if (m.right != null) {
                s.push(m.right);
                continue;
            }
        }
    }

    //模拟了 inorder3
    public static void inorder2(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        while (!s.isEmpty() || root != null) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    public static void inorder3(Node node) {
        if (node == null) return;
        inorder3(node.left);
        System.out.println(node.val);
        inorder3(node.right);
    }
}
