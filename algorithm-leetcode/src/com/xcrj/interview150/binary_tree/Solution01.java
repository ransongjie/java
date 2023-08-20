package com.xcrj.interview150.binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 从前序与中序遍历序列构造二叉树
 */
public class Solution01 {
    /**
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // map<值,索引>
        Map<Integer, Integer> inValIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inValIdx.put(inorder[i], i);
        }

        return f(inValIdx, preorder, 0, preorder.length - 1);
    }

    //前序遍历 从左往右一直是子树的根
    int rootIdx = 0;

    private TreeNode f(Map<Integer, Integer> inValIdx, int[] preorder, int l, int r) {
        if (l <= r) {
            //根据前序遍历列表，获取根节点
            int rootVal = preorder[rootIdx];
            TreeNode root = new TreeNode(rootVal);
            rootIdx++;
            /**
             * [根节点, [左子树的前序遍历结果], [右子树的前序遍历结果]]
             * [[左子树的中序遍历结果], 根节点, [右子树的中序遍历结果]]
             *
             * [rootVal, [左子树的前序遍历结果], [右子树的前序遍历结果]]
             * [[l,...,inValIdx.get(rootVal) - 1], rootVal, [inValIdx.get(rootVal) + 1,...,r]]
             */
            // 先左再右, 因为 前遍历是 [根,左子树,右子树] 一定要从左往右依次处理根结点
            root.left = f(inValIdx, preorder, l, inValIdx.get(rootVal) - 1);
            root.right = f(inValIdx, preorder, inValIdx.get(rootVal) + 1, r);
            return root;
        } else {
            return null;
        }
    }
}
