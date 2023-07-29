package com.xcrj.basic.pass1.ordered_table;

/**
 * 搜索二叉树是AVL, SB, RB的基础
 * AVL, SB, RB他们的区别只在于平衡标准不同
 * 性质
 * - 左子树的值<根节点
 * - 右子树的值>=根节点
 */
public class BST2 {
    class Node {
        int value;
        Node left;
        Node right;
        Node parent;
        public Node(int value){
            this.value=value;
        }
    }
    Node root;
    //结点数量
    int size;
    /**
     * input>curValue: go left; else right
     * @param input
     * @return null or input node
     */
    public Node search(int input) {
        Node cur=root;
        while(cur!=null&&cur.value!=input){
            if(cur.value>input){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }

        return cur;
    }

    /**
     * go to leaf
     * - input<curValue: go left; else right
     * - input<leafValue：insert left; else right
     * @param input
     */
    public void insert(int input) {
        if(root==null){
            root=new Node(input);
            size++;
            return;
        }

        Node leafNode=null;
        Node curNode=root;
        while(curNode!=null){
            leafNode=curNode;
            if(input<curNode.value){
                curNode=curNode.left;
            }else{
                curNode=curNode.right;
            }
        }

        Node newNode=new Node(input);
        if(input<leafNode.value){
            leafNode.left=newNode;
        }else{
            leafNode.right=newNode;
        }

        size++;
    }

    /**
     * @param input
     * @return new root Node
     */
    public Node delete(int input) {
        Node delNode=search(input);
        if(delNode==null) {
            return null;
        }else{
            return delete(delNode);
        }
    }

    /**
     * 无左右子树：直接删除，是否根结点
     * 只有右子树：交换，连接左右孩子，删除
     * 只有左子树：交换，连接左右孩子，删除
     * 有左右子树：交换，连接，删除
     * @param delNode
     * @return new root Node
     */
    private Node delete(Node delNode){
        //无左右子树
        if(delNode.left==null&&delNode.right==null){
            //是否根节点
            if(delNode.parent==null){
                this.root=null;
            }else{
                //是否父节点左孩子
                if(delNode.parent.left==delNode){
                    delNode.parent.left=null;
                }else{
                    delNode.parent.right=null;
                }
            }
            //
            size--;
            return this.root;
        }
        
        //只有右子树
        if(delNode.left==null){
            Node successor=delNode.right;
            //交换
            delNode.value=successor.value;
            delNode=successor;
            //连接
            delNode.parent.left=delNode.left;
            if(delNode.left!=null){
                delNode.left.parent=delNode.parent;
            }
            delNode.parent.right=delNode.right;
            if(delNode.right!=null){
                delNode.right.parent=delNode.parent;
            }
            //删除
            delNode.left=delNode.right=delNode.parent=null;
            //
            size--;
            return this.root;
        }

        //只有左子树
        if(delNode.right==null){
            Node successor=delNode.left;
            //交换
            delNode.value=successor.value;
            delNode=successor;
            //连接
            delNode.parent.left=delNode.left;
            if(delNode.left!=null){
                delNode.left.parent=delNode.parent;
            }
            delNode.parent.right=delNode.right;
            if(delNode.right!=null){
                delNode.right.parent=delNode.parent;
            }
            //删除
            delNode.left=delNode.right=delNode.parent=null;
            //
            size--;
            return this.root;
        }
        
        //有左右子树
        if(delNode.left!=null&&delNode.right!=null){
            Node successor=getSuccessor(delNode.right);
            if(successor.parent==delNode){
                //交换
                delNode.value=successor.value;
                delNode=successor;
                //连接
                delNode.parent.right=delNode.right;
                if(delNode.right!=null){
                    delNode.right.parent=delNode.parent;
                }
                //删除
                delNode.left=delNode.right=delNode.parent=null;
                //
                size--;
                return this.root;
            }else{
                //交换值
                delNode.value=successor.value;
                delNode=successor;
                //连接
                delNode.parent.left=delNode.right;
                if(delNode.right!=null){
                    delNode.right.parent=delNode.parent;
                }
                //删除
                delNode.left=delNode.right=delNode.parent=null;
                //
                size--;
                return this.root;
            }
        }
        return this.root;
    }

    /**
     * 获得后继结点
     * @param delRightNode the right of delNode
     * @return
     */
    private Node getSuccessor(Node delRightNode){
        Node p=delRightNode;
        while(p.left!=null){
            p=p.left;
        }
        return p;
    }
}
