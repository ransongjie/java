package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 020. 回文子字符串的个数
 * - 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * - 字符串由小写字母构成
 */
public class Solution20 {
    /**
     * 中心拓展
     * - 回文串从0开始拓展，回文串长度为奇数，left=0，right=0，编号i=0
     * - 回文串从0开始拓展，回文串长度是偶数，left=0，right=1，编号i=1
     * - 回文串从1开始拓展，回文串长度为奇数，left=1，right=1，编号i=2
     * - 回文串从1开始拓展，回文串长度为偶数，left=1，right=2，编号i=3
     * 编号大小=回文中心数量
     * - 2*len-1
     * 编号与left的关系
     * - 下取整(i/2)
     * 编号与right的关系
     * - left+i%2
     * 
     * 过程：
     * - i: 编号
     * - left right: 根据编号获取中心拓展开始位置
     * - 开始向外拓展判断回文串
     * 
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int count = 0;
        // 编号
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            // 根据编号获取中心拓展开始位置
            int left = i / 2;
            int right = left + i % 2;
            // 开始向外拓展判断回文串
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                // 向外成功拓展1次，则增加1个回文串
                count++;
                left--;
                right++;
            }
        }

        return count;
    }

    /**
     * 利用了之前所有中心拓展的信息的imax，rmax
     * Manacher算法可以求解最长回文子串，在线性时间内
     * 最大回文半径内的每个子回文半径构成的字符串都是回文串
     * 上面的中心拓展过程没有利用之前的中心拓展的信息
     * 改进的Manacher算法利用了之前中心拓展出的最大回文半径信息
     * - imax：前面所有中心拓展 最大回文半径的回文中心
     * - rmax：前面所有中心拓展 最大回文半径的右端点
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int  result=0;
        // 处理s，$#c1#,c2#,c3#!，s.len*2+1+1+1
        StringBuilder sb=new StringBuilder();
        sb.append('$').append("#");
        for(char c:s.toCharArray()){
            sb.append(c);
            sb.append('#');
        }
        sb.append('!');
        
        // 动态规划数组f[i]=从第i个字母出发能够扩展出的最大回文半径
        int[]f=new int[sb.length()-1];
        // 最大回文半径的回文中心
        int imax=0;
        // 最大回文半径的右端点
        int rmax=0;
        /**
         * 利用之前的信息+中心拓展。i=1...sb.length()-2，$和!不需要处理
         * - i>rmax：无法利用之前的所有中心拓展的信息。f[i]=1
         * - i<=rmax：利用i的关于imax的对称点j，f[j] 利用i到rmax的距离，rmax-i+1。f[i]=min{f[j],rmax-i+1}
         */
        for(int i=1;i<sb.length()-1;i++){
            // 利用之前的信息
            f[i]=i>rmax?1:Math.min(f[2*imax-i],rmax-i+1);
            // 中心拓展
            while(sb.charAt(i+f[i])==sb.charAt(i-f[i])){
                f[i]++;
            }
            // 更新最大信息
            if(i+f[i]-1>rmax){
                rmax=i+f[i]-1;
                imax=i;
            }
            // 记录结果。最大回文半径内部的子回文半径构成的字符串也是回文串。/2原因是 c#
            result+=f[i]/2;
        }
        
        return result;
    }
}