package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 086. 分割回文子字符串
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 */
public class Solution86 {

    // 动态规划数组，f[i][j]表示s[i]到s[j]是否为回文串
    // xcrj i~j是否为回文串，要存储两个下标，因此需要二维数组
    boolean[][] fss;
    // 入参字符串长度
    int limit;
    List<List<String>> lss = new ArrayList<>();
    List<String> list = new ArrayList<>();

    /**
     * 回溯法+动态规划
     * 回溯法：深度优先遍历解空间树（结点扩展规则+剪枝函数）
     * 动态规划：将多阶段过程转换为单阶段问题，将单阶段问题的解存储在动态规划数组中
     */
    public String[][] partition1(String s) {
        limit=s.length();
        fss=new boolean[s.length()][s.length()];
        for(boolean[] fs:fss) Arrays.fill(fs, true);

        // 从字符串的最右侧开始
        for(int i=s.length()-1;i>=0;i--){
            // xcrj j+1 因为fss[i+1][j-1]
            for(int j=i+1;j<s.length();j++){
                // 前1个字符串是否是回文串&&当前字符是否相等
                if(fss[i+1][j-1]&&s.charAt(i)==s.charAt(j)) fss[i][j]=true;
                else fss[i][j]=false;
            }
        }

        dfs(s,0);

        // 先定第2维数组数量
        String[][] rss=new String[lss.size()][];
        // xcrj 将数据源lss转储到结果rss，因此for要根据lss操作
        // 操作第1维
        for(int i=0;i<lss.size();i++){
            // 操作第2维
            List<String> ls=lss.get(i);
            rss[i]=new String[ls.size()];
            for(int j=0;j<ls.size();j++){
                rss[i][j]=ls.get(j);
            }
        }

        return rss;
    }

    /**
     * xcrj
     * 深度，1个元素是否为回文串
     * 回溯
     * 深度，2个元素是否为回文串
     * 回溯
     * ...
     * xcrj i~j控制元素个数
     * @param s 子串
     * @param i 左指针
     */
    private void dfs(String s, int i) {
        // 求得解
        if(i==limit){
            lss.add(new ArrayList<>(list));
            return;
        }

        // j是右指针
        for(int j=i;j<limit;j++){
            if(fss[i][j]){
                list.add(s.substring(i,j+1));
                // 深度
                dfs(s,j+1);
                // 回溯
                list.remove(list.size()-1);
            }
        }
    }

    int[][] fis;
    /**
     * 回溯法+记忆搜索
     * 记忆搜索优化了 partition1() 中的判断回文串的两重for循环
     * xcrj 记忆搜索优势，记录了搜索的中间结果，例如，判断s[i]~s[j]是否为回文串的中间s[i+1]~s[j-1]是否为回文串
     */
    public String[][] partition2(String s) {
        limit=s.length();
        fis=new int[s.length()][s.length()];

        backTrack(s, 0);

        String[][] rss=new String[lss.size()][];
        for(int i=0;i<lss.size();i++){
            List<String> ls=lss.get(i);
            rss[i]=new String[ls.size()];
            for(int j=0;j<ls.size();j++){
                rss[i][j]=ls.get(j);
            }
        }

        return rss;
    }

    private void backTrack(String s, int i) {
        // 获取到结果
        if(i==this.limit){
            lss.add(new ArrayList<>(list));
            return;
        }

        for(int j=i;j<limit;j++){
            if(1==searchPalindrome(s, i, j)){
                list.add(s.substring(i, j+1));
                //xcrj s[i]~s[j]是回文串，已经加入list，i=j+1判断下1个回文串
                backTrack(s, j+1);
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 记忆搜索，判断回文串
     * f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
     */
    private int searchPalindrome(String s, int i, int j) {
        // xcrj 记忆搜索的好处在这里得到体现，直接使用记录的搜索中间结果
        if(0!=fis[i][j])return fis[i][j];

        // xcrj [j,i]，i到j右边了直接赋值为1，一般情况的searchPalindrome会造成i>=j
        if(i>=j){
            fis[i][j]=1;
            return fis[i][j];
        }

        // 一般情况
        if(s.charAt(i)==s.charAt(j)){
            fis[i][j]=searchPalindrome(s, i+1, j-1);
            return fis[i][j];
        }

        fis[i][j]=-1;
        return fis[i][j];
    }
}
