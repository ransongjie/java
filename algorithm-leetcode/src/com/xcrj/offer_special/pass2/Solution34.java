package com.xcrj.offer_special.pass2;
/**
 * 剑指 Offer II 034. 外星语言是否排序
 * order是字母表
 * words是几个单词组成的一段话
 * words中单词相同位置字符满足字典order顺序
 * 空白字符位于字典order的最后，空白字符排在最后，它的序号最大
 */
public class Solution34 {
    public boolean isAlienSorted(String[] words, String order) {
        // 统计order的序号
        // idxs[字母]=序号
        int[]idxs=new int[26];
        for(int i=0;i<order.length();i++){
            char c=order.charAt(i);
            idxs[c-'a']=i;
        }

        // 判断两个单词是否按字典序
        for(int i=1;i<words.length;i++){
            boolean flag=false;
            // xcrj 只要相同位置首个不相同字母按字典序即可
            for(int j=0;j<words[i-1].length()&&j<words[i].length();j++){
                int orderPre=idxs[words[i-1].charAt(j)-'a'];
                int orderNext=idxs[words[i].charAt(j)-'a'];
                // 前1个单词的字符序号>后1个单词的字符序号 直接返回false
                if(orderPre>orderNext){
                    return false;
                }
                // 前1个单词的字符序号<后1个单词的字符序号 比较下两个单词
                // xcrj 因为是字典序 只要首个不相同字母按字典序即可 所以直接比较下两个单词
                if(orderPre<orderNext){
                    flag=true;
                    break;
                }
            }
            // xcrj 上面for循环遍历完某1个单词都没有找到满足条件的，则比较长度，空白字符排在最后，它的序号最大
            if(!flag){
                if (words[i - 1].length() > words[i].length()) return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        Solution34 solution34=new Solution34();
        solution34.isAlienSorted(new String[]{"apap","app"}, "abcdefghijklmnopqrstuvwxyz");
    }
}
