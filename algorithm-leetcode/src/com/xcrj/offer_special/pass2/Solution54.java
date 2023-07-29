package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 * 所有大于等于节点的值之和，将二叉搜索树的当前及后继结点的值求和赋值给当前结点
 * 中序遍历二叉搜索树得到递增序列
 * 反中序遍历二叉搜索树得到递减序列
 */
public class Solution54 {
    int sum;
    /**
     * 反中序遍历
     * @param root
     * @return
     */
    public TreeNode convertBST1(TreeNode root) {
        if(root==null) return null;
        
        convertBST1(root.right);
        sum+=root.val;
        root.val=sum;
        convertBST1(root.left);
        return root;
    }

    /**
     * Morris动态线索二叉树
     * - O(n)：没有右子树的结点只达到1次，有右子树的结点到达2次
     * - O(1)：利用数的空闲指针，实现空间开销的缩减
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        TreeNode p=root;
        int sum=0;
        while(p!=null){
            // 先考虑无前继结点的情况，因为无前继结点时，p.val=sum+p.val
            if(p.right==null){
                sum+=p.val;
                p.val=sum;
                p=p.left;
            }
            // 再考虑有前继结点的情况，有前继结点则先把前继结点处理完，最后再处理自己
            else{
                // 如何判断前继结点已经处理完，看当前结点的直接前驱结点的left是否为null
                TreeNode pioneer=getPioneer(p);
                // null,则表示当前结点的前驱结点未被处理，
                if(pioneer.left==null){
                    pioneer.left=p;
                    // 处理前驱结点
                    p=p.right;
                }
                // !null,则表示当前结点的前驱结点已被处理
                else{
                    pioneer.left=null;
                    // 处理当前结点
                    sum+=p.val;
                    p.val=sum;
                    p=p.left;
                }
            }

        }

        return root;
    }

    /**
     * 获取当前结点的直接前驱
     * @param node
     * @return
     */
    public TreeNode getPioneer(TreeNode node) {
        TreeNode p=node.right;
        // p.left!=null
        // p.left!=node 线索二叉树的构建可能造成环
        while(p.left!=null&&p.left!=node){
            p=p.left;
        }
        return p;
    }
}
