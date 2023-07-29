package com.xcrj.basic.pass1.tree;
//前序遍历序列化
public class PreOrderSerialize {
    public static void main(String[] args) {
        Node node1=new Node(1);
        Node node2=new Node(2);
        Node node3=new Node(3);
        Node node4=new Node(4);
        Node node5=new Node(5);
        Node node6=new Node(6);
        Node node7=new Node(7);
        //
        node1.left=node2; node1.right=node3;
        node2.left=node4;node2.right=node5;node3.left=node6;
        node3.right=node7;
        node4.left=node4.right=null;node5.left=node5.right=null;node6.left=node6.right=null;
        node7.left=node7.right=null;
        //
        Node root=node1;
        // Util.printTree(root);
        StringBuilder sb=new StringBuilder();
        serialize(root, sb);
        System.out.println(sb.toString());

        String str=serialize2(root);
        System.out.println(str);
    }

    public static void serialize(Node root,StringBuilder sb) {
        if(root==null) {sb.append("#_");return;}
        sb.append(root.val+"_");
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    /**
     * #_2_#_4_5_
     * @param root
     * @return
     */
    public static String serialize2(Node root) {
        if(root==null) return "#_";
        String str=root.val+"_";
        str+=serialize2(root.left);
        str+=serialize2(root.right);
        return str;
    }
}
