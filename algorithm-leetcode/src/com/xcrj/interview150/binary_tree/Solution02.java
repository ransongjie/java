package com.xcrj.interview150.binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 从中序与后序遍历序列构造二叉树
 */
public class Solution02 {
    /**
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        rootIdx = postorder.length - 1;
        // map<值,索引>
        Map<Integer, Integer> inValIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inValIdx.put(inorder[i], i);
        }

        return f(inValIdx, postorder, 0, postorder.length - 1);
    }

    //前序遍历 从左往右一直是子树的根
    int rootIdx;

    private TreeNode f(Map<Integer, Integer> inValIdx, int[] postorder, int l, int r) {
        if (l <= r && rootIdx > -1) {
            //根据前序遍历列表，获取根节点
            int rootVal = postorder[rootIdx];
            TreeNode root = new TreeNode(rootVal);
            rootIdx--;
            /**
             * [[左子树的前序遍历结果], [右子树的前序遍历结果], 根节点]
             * [[左子树的中序遍历结果], 根节点, [右子树的中序遍历结果]]
             *
             * [[左子树的前序遍历结果], [右子树的前序遍历结果], rootVal]
             * [[l,...,inValIdx.get(rootVal) - 1], rootVal, [inValIdx.get(rootVal) + 1,...,r]]
             */
            // 先右再左, 因为 后续遍历是 [左子树,右子树,根] 一定要从右往左依次处理根结点
            root.right = f(inValIdx, postorder, inValIdx.get(rootVal) + 1, r);
            root.left = f(inValIdx, postorder, l, inValIdx.get(rootVal) - 1);
            return root;
        } else {
            return null;
        }
    }
}
