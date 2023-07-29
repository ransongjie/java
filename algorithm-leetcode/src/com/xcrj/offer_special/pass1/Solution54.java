package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * - 节点的左子树仅包含键 小于 节点键的节点。
 * - 节点的右子树仅包含键 大于 节点键的节点。
 * - 左右子树也必须是二叉搜索树。
 * <p>
 * 二叉搜索树就是二叉排序树
 * 性质
 * - 节点的左子树仅包含键 小于 节点键的节点。
 * - 节点的右子树仅包含键 大于 节点键的节点。
 * - 左右子树也必须是二叉搜索树。
 * - 中序遍历二叉搜索树得到递增序列
 * - 反中序遍历二叉搜索树得到递减序列
 *
 * 所有大于等于节点的值之和，将二叉搜索树的当前及后继结点的值求和赋值给当前结点
 */
public class Solution54 {
    int sum;

    /**
     * 利用二叉搜索树的性质，饭中序遍历二叉搜索树，得到递减序列
     */
    public TreeNode convertBST1(TreeNode root) {
        if (null == root) return null;

        convertBST1(root.right);
        sum += root.val;
        // 替换成树中>=root.val的所有节点值之和，sum包括root.val
        root.val = sum;
        convertBST1(root.left);

        return root;
    }

    /**
     * Morris遍历构建和使用线索二叉树（动态线索二叉树）
     * <p>
     * Morris遍历好处和来源
     * - 在线性时间内，只占用常数空间来实现中序遍历
     * - “Traversing Binary Trees Simply and Cheaply” J. H. Morris 在 1979 年发表的论文
     * <p>
     * Morris遍历核心思想：利用数的空闲指针，实现空间开销的缩减
     * <p>
     * Morris反中序遍历过程：
     * - 当前结点的后继结点：若当前节点的右指针为空，处理当前节点，并遍历当前结点的左孩子结点
     * - 找到当前结点的直接前驱结点：若当前节点的右指针非空，找到当前结点右子树的最左结点（反中序遍历的前驱结点）
     * -- 构建线索二叉树，直接前驱结点指向当前结点：若最左结点的左指针为空，将最左结点的左指针指向当前结点，遍历当前结点的右子结点
     * -- 使用线索二叉树，当前结点的线索结点：若最左结点的左指针非空，将最左结点的左指针重置为空（恢复树的原状），处理当前节点，并将当前结点置为其左结点
     * <p>
     * Morris反中序遍历时间复杂度：
     * - O(n)：没有右子树的结点只达到1次，有右子树的结点到达2次
     * - O(1)：利用数的空闲指针，实现空间开销的缩减
     */
    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (null != node) {
            // 目的：构建线索二叉树，直接前驱结点指向当前结点
            // 当前结点没有右孩子，则没有反中序遍历前驱，处理左子树，则处理反中序遍历后继结点，即val更大的结点
            // 若当前结点右指针为空，处理当前结点，遍历当前结点的左孩子结点
            if (null == node.right) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            // 目的：找到当前结点的直接前驱结点
            // 若当前结点右指针非空，找到当前结点右子树的最左结点（反中序遍历的前驱结点）
            else {
                TreeNode pioneer = getPioneer(node);
                // 目的：直接前驱结点指向当前结点
                // 若当前结点右指针非空，若右孩子的最左结点的左指针为空，将最左结点的左指针指向当前结点，遍历当前结点的右子结点
                if (null == pioneer.left) {
                    pioneer.left = node;
                    node = node.right;
                }
                // 目的：使用线索二叉树，当前结点的线索结点
                // 若当前结点右指针非空，若右孩子的最左结点的左指针非空（因为这个条件node != pioneer.left退出），将最左结点的左指针重置为空（恢复树的原状），处理当前节点，并将当前结点置为其左结点
                else {
                    pioneer.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    /**
     * 获取当前结点的反中序遍历的直接前驱结点
     */
    public TreeNode getPioneer(TreeNode node) {
        // 上一个函数已经知道right非空
        TreeNode pioneer = node.right;
        // node != pioneer.left 防止成环
        while (null != pioneer.left && node != pioneer.left) {
            pioneer = pioneer.left;
        }
        return pioneer;
    }
}
