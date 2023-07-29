package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 048. 序列化与反序列化二叉树
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Solution48 {
    /**
     * 深度优先 先序遍历
     * 先序遍历 序列化
     * 先序遍历 反序列化
     */
    public class Codec1 {

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            reSerialize(root, sb);
            return sb.toString();
        }

        /**
         * 递归进行 先序遍历 序列化
         * 需要将空指针置为None，否则不知道在什么地方结束
         */
        public String reSerialize(TreeNode root, StringBuilder sb) {
            if (null == root) {
                return sb.append("None").append(",").toString();
            }

            // 根
            sb.append(root.val).append(",");
            // 左
            reSerialize(root.left, sb);
            // 右
            reSerialize(root.right, sb);

            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            String[] strs = data.split(",");
            List<String> vals = new ArrayList<>(Arrays.asList(strs));
            TreeNode root = reDeserialize(vals);
            return root;
        }

        /**
         * 递归进行 先序遍历 反序列化
         */
        public TreeNode reDeserialize(List<String> vals) {
            if ("None".equals(vals.get(0))) {
                vals.remove(0);
                return null;
            }

            // 根
            TreeNode node = new TreeNode(Integer.valueOf(vals.get(0)));
            vals.remove(0);
            // 左
            node.left = reDeserialize(vals);
            // 右
            node.right = reDeserialize(vals);

            return node;
        }
    }

    /**
     * 括号表示编码+递归下降解码
     */
    public class Codec2 {
        /**
         * 后序遍历 序列化
         * T -> (T) num (T) | X：T的内部是|左边的模式串 或者|右边的X
         * - 开头是 X，我们就知道这一定是解析一个「空树」的结构
         * - 开头是 (，我们就知道需要解析 (T) num (T) 的结
         * <p>
         * 这种序列化过程是 LL(1) 型文法：它定义了一种递归的方法来反序列化，也保证了这个方法的正确性
         */
        public String serialize(TreeNode root) {
            if (null == root) return "X";

            String sl = "(" + serialize(root.left) + ")";
            String sr = "(" + serialize(root.right) + ")";
            return sl + root.val + sr;
        }

        /**
         * 序列化是LL(1) 型文法：使用递归的方法来反序列化
         * - ptr指向元素为X说明解析到了一棵空树，直接返回
         * - 否则ptr指向元素一定为(, 对括号内部按照 (T) num (T) 的模式解析
         */
        public TreeNode deserialize(String data) {
            int[] ptr = {0};
            TreeNode root = parse(data, ptr);
            return root;
        }

        /**
         * 解析T
         *
         * @param data 序列化后的字符串
         * @param ptr  待解析的字符
         */
        public TreeNode parse(String data, int[] ptr) {
            // ptr指向元素为X说明解析到了一棵空树，直接返回
            if ('X' == data.charAt(ptr[0])) {
                ptr[0]++;
                return null;
            }

            // 否则ptr指向元素一定为(, 对括号内部按照 (T) num (T) 的模式解析
            TreeNode node = new TreeNode();
            node.left = parseSubTree(data, ptr);
            // 不使用Integer.valueOf(data.charAt(ptr[0]));
            node.val = parseInt2(data, ptr);
            node.right = parseSubTree(data, ptr);
            return node;
        }

        /**
         * 解析(T) num (T)中的T
         */
        public TreeNode parseSubTree(String data, int[] ptr) {
            // 跳过左括号
            ptr[0]++;
            TreeNode subTree = parse(data, ptr);
            // 跳过右括号
            ptr[0]++;

            return subTree;
        }

        /**
         * 解析整型 有符号数
         */
        public int parseInt(String data, int[] ptr) {
            // sgn代表正负号，data中是有符号数
            int x = 0, sgn = 1;
            // 不是数字是负号
            if (!Character.isDigit(data.charAt(ptr[0]))) {
                sgn = -1;
                ptr[0]++;
            }
            // 解析数字
            while (Character.isDigit(data.charAt(ptr[0]))) {
                x = x * 10 + data.charAt(ptr[0]++) - '0';
            }
            return x * sgn;
        }

        /**
         * (T) num (T)
         * - num夹在)(括号之间
         * - num是有符号数
         */
        public int parseInt2(String data, int[] ptr) {
            // 统计到(为止有多少字符
            int count = 0;
            while ('(' != data.charAt(ptr[0]++)) count++;
            // 提出(括号
            ptr[0] = ptr[0] - 1;
            return Integer.valueOf(data.substring(ptr[0] - count, ptr[0]));
        }

    }

    public static void main(String[] args) {
        Solution48 solution48 = new Solution48();
        Codec2 codec2 = solution48.new Codec2();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;
        TreeNode node3 = new TreeNode(3);
        node1.right = node3;
        TreeNode node4 = new TreeNode(4);
        node3.left = node4;
        TreeNode node5 = new TreeNode(5);
        node3.right = node5;

        String data = codec2.serialize(node1);
        codec2.deserialize(data);
    }
}
