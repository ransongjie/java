package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 048. 序列化与反序列化二叉树
 */
public class Solution48 {
    /**
     * 深度优先 先序遍历
     */
    public class Codec1 {
        public String serialize(TreeNode root) {
            StringBuilder sb=new StringBuilder();
            reSerialize(root, sb);
            return sb.toString();
        }

        public String reSerialize(TreeNode root, StringBuilder sb) {
            // xcrj 有root为null的判断 左右子孩子为null就不用再判断了
            if(root==null){
                sb.append("none").append(',');
                return sb.toString();
            }

            sb.append(root.val).append(',');
            reSerialize(root.left, sb);
            reSerialize(root.right, sb);
            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            String[] vals=data.split(",");
            // xcrj new ArrayList<>(入参list)
            List<String> list=new ArrayList<>(Arrays.asList(vals));
            return reDeserialize(list);
        }

        public TreeNode reDeserialize(List<String> vals) {
            if("none".equals(vals.get(0))){
                vals.remove(0);
                return null;
            }

            TreeNode node=new TreeNode(Integer.valueOf(vals.get(0)));
            vals.remove(0);
            // xcrj 构造左子树
            node.left=reDeserialize(vals);
            // xcrj 构造右子树
            node.right=reDeserialize(vals);
            return node;
        }
    }

    /**
     * 括号表示编码+递归下降解码
     */
    public class Codec2 {
        /**
         * LL(1)型序列化文法
         * T -> (T) num (T) | X
         * - 开头是 X，我们就知道这一定是解析一个「空树」的结构
         * - 开头是 (，我们就知道需要解析 (T) num (T) 的结
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            if(null==root) return "X";
            String sl="("+serialize(root.left)+")";
            String s2="("+serialize(root.right)+")";
            return sl+root.val+s2;
        }

        /**
         * LL(1)型反序列化文法
         * - ptr指向X说明解析到了一棵空树，直接返回
         * - 否则ptr指向元素一定为(, 对括号内部按照 (T) num (T) 的模式解析
         * @param data
         * @return
         */
        public TreeNode deserialize(String data) {
            // xcrj 数组是引用 为了元素值的变化处处可见
            int [] ptr={0};
            return parse(data, ptr);
        }

        /**
         * 解析T-> (T) num (T) | X
         * - 开头是 X，我们就知道这一定是解析一个「空树」的结构
         * - 开头是 (，我们就知道需要解析 (T) num (T) 的结
         * @param data
         * @param ptr
         * @return
         */
        public TreeNode parse(String data, int[] ptr) {
            // 解析X
            if(data.charAt(ptr[0])=='X'){
                ptr[0]++;
                return null;
            }

            TreeNode node=new TreeNode();
            // 解析T T是子树
            node.left=parseSubTree(data,ptr);
            // 解析num
            node.val=parseInt2(data, ptr);
            // 解析T
            node.right=parseSubTree(data, ptr);
            return node;
        }

        /**
         * 
         * @param data
         * @param ptr
         * @return
         */
        public TreeNode parseSubTree(String data, int[] ptr) {
            ptr[0]++;
            TreeNode node=parse(data, ptr);
            ptr[0]++;
            return node;
        }

        /**
         * 解析有符号数 -231
         * @param data
         * @param ptr
         * @return
         */
        public int parseInt(String data, int[] ptr) {
            int sgn=1;
            if(!Character.isDigit(data.charAt(ptr[0]))){
                sgn=-1;
                ptr[0]++;
            }

            // xcrj 字符数字转整型数字
            int x=0;
            // (T)num(T) num都是数字
            while(Character.isDigit(ptr[0])){
                x=x*10+data.charAt(ptr[0]++)-'0';
            }
            return sgn*x;
        }

        /**
         * 第2中方式解析有符号数 -231
         * @param data
         * @param ptr
         * @return
         */
        public int parseInt2(String data, int[] ptr) {
            int count=0;
            while(data.charAt(ptr[0]++)!='(') count++;
            // 剔除(
            ptr[0]--;
            return new Integer(data.substring(ptr[0]-count, ptr[0]));
        }
    }
}
