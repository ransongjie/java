package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 085. 生成匹配的括号
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
 * 
 * 分析：
 * - n=左括号数量=右括号数量
 * - 只有括号，没有数字
 */
public class Solution85 {
    List<String> rs = new ArrayList<>();

    /**
     * 蛮力法
     * - 添加所有可能的括号序列
     * - 检查添加的括号序列是否合法
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        // n对括号，从第0个位置开始放置括号
        allBrackets(new char[2*n], 0);
        return rs;
    }

    // 在第i个位置放置括号
    private void allBracketsErr(char[] brackets, int i) {
        // 求得解
        if(i==brackets.length&&valid(brackets)){
            rs.add(new String(brackets));
            return;
        }

        // 先放置(
        /// 第i个位置放置(
        brackets[i]='(';
        allBrackets(brackets, i+1);
        // 再放置)
        /// 第i个位置放置)
        brackets[i]=')';
        allBrackets(brackets, i+1);
    }

    // 在第i个位置放置括号
    private void allBrackets(char[] brackets, int i) {
        // 求得解
        // xcrj if(i==brackets.length&&valid(brackets)) 是错误写法，不管是不是有效的括号对序列，达到len就要return
        // xcrj 不管是否满足条件，都要退出
        if(i==brackets.length){
            if(valid(brackets)) rs.add(new String(brackets));
            return;
        }

        // 先放置(
        /// 第i个位置放置(
        brackets[i]='(';
        allBrackets(brackets, i+1);
        // 再放置)
        /// 第i个位置放置)
        brackets[i]=')';
        allBrackets(brackets, i+1);
    }

    private boolean valid(char[] brackets) {
        // 计数器，判断平衡
        int count=0;
        for(char c:brackets){
            if(c=='(') count++;
            else count--;
            // 快速失败
            if(count<0) return false;
        }

        return count==0;
    }

    // 回溯法
    public List<String> generateParenthesis2(int n) {
        backTrack(n, 0, 0);
        return rs;
    }

    StringBuilder sb = new StringBuilder();

    /**
     * 回溯
     *
     * @param limit 限制长度，括号对数
     * @param open  左括号数量
     * @param close 右括号数量
     */
    private void backTrack(int limit, int open, int close) {
        if(limit*2==sb.length()){
            rs.add(sb.toString());
            return;
        }

        // 先把左括号添加完，再添加右括号，从叶子结点开始回溯
        // 先添加左括号
        if(open<limit){
            sb.append('(');
            backTrack(limit,open+1,close);
            // 回溯
            sb.deleteCharAt(sb.length()-1);
        }
        // 再添加右括号
        // xcrj close<open
        if(close<open){
            sb.append(')');
            backTrack(limit,open,close+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    
}
