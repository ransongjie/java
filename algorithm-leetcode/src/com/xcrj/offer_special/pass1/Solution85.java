package com.xcrj.offer_special.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 085. 生成匹配的括号
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
 */
public class Solution85 {
    List<String> rs = new ArrayList<>();

    /**
     * 蛮力法
     * - 添加所有可能的括号序列
     * - 检查添加的括号序列是否合法
     */
    public List<String> generateParenthesis1(int n) {
        // 左右括号2*n。0，初始在brackets[0]添加括号
        allBrackets(new char[2 * n], 0);
        return rs;
    }

    /**
     * 生成所有括号
     *
     * @param brackets 括号数组
     * @param i        在brackets[i]添加括号
     */
    private void allBrackets(char[] brackets, int i) {
        // brackets括号添加完毕
        if (i == brackets.length) {
            if (valid(brackets)) {
                this.rs.add(new String(brackets));
            }
        }
        // 在brackets[i]添加括号
        else {
            // 在brackets[i]添加左括号
            brackets[i] = '(';
            // 再在brackets[i+1]添加括号
            allBrackets(brackets, i + 1);
            // 在brackets[i]添加右括号
            brackets[i] = ')';
            // 再在brackets[i+1]添加括号
            allBrackets(brackets, i + 1);
        }
    }

    /**
     * 有效的括号对
     */
    private boolean valid(char[] brackets) {
        // 是否平衡
        int balance = 0;
        for (char c : brackets) {
            if (c == '(') {
                balance++;
            }
            // c==')'
            else {
                balance--;
            }
            // 右括号数量多余左括号
            if (balance < 0) return false;
        }
        // 左右括号匹配则balance=0 返回true，不匹配则balance!=0 返回false
        return balance == 0;
    }


    /**
     * 回溯法
     * - 确定结点的扩展搜索规则之后，以深度优先方式搜索解空间树，在搜索过程中采用剪枝函数来避免无用搜索。
     * 解空间树
     * 剪枝函数
     * - 约束函数：剪去不满足约束条件的子树
     * - 限界函数：剪去得不到需要解（无解，非最优解）的子树
     * <p>
     * 有效括号
     * - 若左括号数量<limit，可以继续添加左括号
     * - 若右括号数量<左括号数量，可以继续添加右括号
     */
    public List<String> generateParenthesis2(int n) {
        // n=左括号数量=右括号数量。0初始在brackets[0]添加括号，左括号数量初始为0，右括号数量初始为0
        backTrack(n, 0, 0);
        return rs;
    }

    // 括号StringBuilder
    StringBuilder sb = new StringBuilder();

    /**
     * 回溯
     *
     * @param limit 限制长度
     * @param open  左括号数量
     * @param close 右括号数量
     */
    private void backTrack(int limit, int open, int close) {
        // brackets括号添加完毕
        // 往sb中添加左右括号
        if (this.sb.length() == limit * 2) {
            this.rs.add(this.sb.toString());
            return;
        }
        // 若左括号数量<limit
        if (open < limit) {
            // 添加左括号
            this.sb.append('(');
            // 再在brackets[i+1]添加括号
            backTrack(limit, open + 1, close);
            // 回溯，删除添加的左括号
            this.sb.deleteCharAt(this.sb.length() - 1);
        }
        // 若右括号数量<左括号数量
        if (close < open) {
            // 添加右括号
            this.sb.append(')');
            // 再在brackets[i+1]添加括号
            backTrack(limit, open, close + 1);
            // 回溯，删除添加的右括号
            this.sb.deleteCharAt(this.sb.length() - 1);
        }
    }


    /**
     * 递归ab枚举第1个(对应的)的位置
     * 已知：
     * - 任何一个括号序列都一定是由(开头，并且第一个(一定有一个唯一与之对应的 )。
     * - 这样一来，每一个括号序列可以用(a)b来表示，其中a与b分别是一个合法的括号序列（a b可以为空字符串）。
     * 分析：(a)b
     * - 最里面的括号a和b都为空字符串：()
     * -
     */
    public List<String> generateParenthesis3(int n) {
        return generateab(n);
    }

    // 缓存递归运算的值，减少递归运算次数
    // 存储递归值，避免再递归运算。ArrayList对象数组，里面存储1个个list。
    List[] cache = new ArrayList[100];

    /**
     * 生产a b
     */
    private List<String> generateab(int n) {
        // 判断缓存中是否有值有直接返回，不用再递归
        if (null != this.cache[n]) {
            return cache[n];
        }

        List<String> list = new ArrayList<>();
        // 0初始情况，放入空字符串
        if (0 == n) {
            list.add("");
        }
        // 非初始情况
        else {
            //
            for (int i = 0; i < n; i++) {
                // 生产a。i左括号位置
                for (String a : generateab(i)) {
                    // 生产b。n - 1 - i,i左括号位置对应的右括号位置
                    for (String b : generateab(n - 1 - i)) {
                        // 遍历a与b的所有可能性并拼接
                        list.add("(" + a + ")" + b);
                        System.out.println("(" + a + ")" + b);
                    }
                }
            }
        }

        // 缓存递归结果
        this.cache[n] = list;
        return list;
    }

    public static void main(String[] args) {
        Solution85 solution85 = new Solution85();
        solution85.generateab(3);
    }
}
