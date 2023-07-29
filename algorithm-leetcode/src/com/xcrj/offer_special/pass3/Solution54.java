package com.xcrj.offer_special.pass3;
/**
 * 剑指 Offer II 054. 二叉搜索树此结点的值=sum(大于此结点值)
 */
public class Solution54 {
    int sum;
    /**
     * 反中序遍历, 从大到小排列
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if(root==null) return null;
        //
        convertBST(root.right);
        sum+=root.val;
        root.val=sum;
        convertBST(root.left);
        //
        return root;
    }

    /**
     * 动态线索二叉树，在构建线索二叉树的过程中，操作sum
     * 使用node的 空left 指向 node的后继（反中序遍历前驱）
     * Morris动态线索二叉树
     * - O(n)：没有右子树的结点只达到1次，有右子树的结点到达2次
     * - O(1)：利用数的空闲指针，实现空间开销的缩减
     * @param root
     * @return
     */
    public TreeNode convertBST2(TreeNode root) {
        TreeNode p=root;
        int sum=0;
        //
        while(p!=null){
            // p结点无前继结点
            if(p.right==null){
                sum+=p.val;
                p.val=sum;
                p=p.left;
            }
            // p结点有前继结点
            else{
                // 获取p结点的直接前继结点
                TreeNode pre=getPioneer(p);
                // 构建线索二叉树，处理p结点的前驱结点
                if(pre.left==null){
                    pre.left=p;
                    // xcrj
                    p=p.right;
                }
                // left指向后驱结点，处理p结点的后驱结点
                else{
                    // pre.left等于p
                    pre.left=null;
                    sum+=p.val;
                    p.val=sum;
                    // xcrj
                    p=p.left;
                }

            }
        }
        //
        return root;
    }

    /**
     * 获取node结点直接前继结点，右孩子的最左子结点
     * @param node
     * @return
     */
    public TreeNode getPioneer(TreeNode node) {
        TreeNode p=node.right; 
        //p.left!=node 防止环
        while(p.left!=null&&p.left!=node){
            p=p.left;
        }
        return p;
    }
}
