package com.xcrj.offer_special.pass3;
/**
 * 剑指 Offer II 018. s是否为回文串
 * - 只考虑字母和数字字符
 * - 忽略字母的大小写。
 */
public class Solution18 {
    /**
     * 双指针相向移动
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int i =0;
        int j=s.length()-1;
        while(i<j){
            //跳过非字母和数字字符
            while(i<j&&!Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i<j&&!Character.isLetterOrDigit(s.charAt(j)))j--;
            //忽略字母大小写而比较相等
            if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                return false;
            }else{
                i++;
                j--;
            }
        }

        return true;
    }
}
