package com.xcrj.offer_special.pass1;

/**
 * 剑指 Offer II 020. 回文子字符串的个数
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 字符串由小写字母构成
 */
public class Solution20 {
    /**
     * 中心拓展，从回文中心向两边拓展，当前回文中心不能继续拓展时操作下一个回文中心
     * 回文中心位置，考虑回文长度是奇数或偶数的情况
     * ！！！找到编号和回文中心下标、left、right的关系
     * s.len=4时，回文中心数量为2*len-1，回文中心编号从[0,i,2*len-2], left=下取整(i/2)，right=left+i%2
     * 编号i=0 回文中心下标为0 回文数长度为奇数 left=0 right=0
     * 编号i=1 回文中心下标为0 回文数长度为偶数 left=0 right=1
     * 编号i=2 回文中心下标为1 回文数长度为奇数 left=1 right=1
     * 编号i=3 回文中心下标为1 回文数长度为偶数 left=1 right=2
     * 编号i=4 回文中心下标为2 回文数长度为奇数 left=2 right=2
     * 编号i=5 回文中心下标为2 回文数长度为偶数 left=2 right=3
     * 编号i=6 回文中心下标为3 回文数长度为奇数 left=3 right=3
     */
    public int countSubstrings1(String s) {
        int count = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            // 从每个回文中心向外拓展，寻找回文数
            int l = i / 2;
            int r = l + i % 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count++;
                l--;
                r++;
            }
        }
        return count;
    }

    /**
     * ！！Manacher 算法是在线性时间内求解最长回文子串的算法，最大回文半径内的次回文半径也是回文数
     * 中心拓展是一步步的从中心往外拓展而没有利用之前的中心拓展出来的信息而改进的Manacher算法利用了这个信息
     * ！！f[i]元素i的最大回文半径 动态规划数组，利用之前中心拓展的最大回文半径求解
     */
    public int countSubstrings2(String s) {
        // Manacher所需特殊处理
        StringBuilder sb = new StringBuilder(s.length() * 2 + 3);
        // 字符串开始加入$,末尾加入!是为了防止越界 保证到达字符串两端点时字符一定不相等
        // $和!是哨兵，防止while (sb.charAt(i + f[i]) == sb.charAt(i - f[i]))时越界
        sb.append('$');
        sb.append('#');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            // Manacher要求在相邻字符间和字符两端加入#，将原字符串统一变成奇数长度的字符串，就不用将字符串分开为奇偶处理
            sb.append('#');
        }
        sb.append('!');

        /**
         * f(i)=第i个元素的最大回文半径长度=以第i个元素为回文中心最多能向外扩展的元素个数
         * 存储以第i个元素（#？？）为中心的最大回文半径长度=f(i)，i元素的最大回文半径长度是指以第i个元素为回文中心最多能向外扩展的元素个数
         * 为什么-1，sb最后1个元素!只是为了防止越界
         * 为什么了f[]的长度是sb.len-1，f[0]=0，记录第0个元素$（没有任何元素）时最长回文半径为0，f[1]~f[sb.length() - 2]
         * f[2 * imax - i]中2 * imax - i可能等于0即 2*1-2,=0，第2个元素关于imax=1的对称位置是0
         */
        int[] f = new int[sb.length() - 1];
        // 那个元素具有最大的回文半径，已经处理过的元素的最大回文半径f(i)我们都知道了，求max{f(1), f(2), ..., f(k)}的下标i
        // 为什么初始值为0，求最大值，最值都大于0
        int imax = 0;
        // 具有最大回文半径的元素的右端点，圆边上
        // 为什么初始值为0，求最大值，最值都大于0
        int rmax = 0;
        // 结果回文子字符串的个数
        int result = 0;
        // 为什么从1开始，sb第1个元素$只是为了防止越界的
        // 为什么-1，sb最后1个元素!也只是为了防止越界的
        for (int i = 1; i < sb.length() - 1; i++) {
            /**
             *
             * 已知，
             *  1,...,i-1的f[1,...,i-1]（每个元素的最长回文半径）和imax, rmax
             *  lmax,...,j,...,imax,...,i,...,rmax
             *
             * 初始化f[]
             *   2 * imax - i=i关于imax的对称点j
             *
             * 当i<=rmax时，
             *   情况1f[i]<=rmax-i+1，f[i]至少=f[j]：f[i]=min{rmax-i+1,f(j)}，i周围元素=j周围元素，当j左边元素=j右边元素时，i左边元素=i右边元素，也就是f[i]至少=f[j]
             *   情况2f[i]>=rmax-i+1，f[i]至少=rmax-i+1：f[j]的左边界可能超过lmax，即f[i]的右边界可能超过rmax，所以f[i]至少=rmax-i+1
             * 当i>=rmax时，
             *   f[i]=1，也就是第i个元素自身是回文数
             */
            f[i] = i <= rmax ? Math.min(rmax - i + 1, f[2 * imax - i]) : 1;
            // 中心拓展，f[i]是以第i个元素为中心的回文半径，也就是向外扩展的元素个数
            while (sb.charAt(i + f[i]) == sb.charAt(i - f[i])) {
                ++f[i];
            }
            // 更新imax和rmax
            if (i + f[i] - 1 > rmax) {
                rmax = i + f[i] - 1;
                imax = i;
            }
            /**
             * 结果，第i个元素的最大回文半径/2取整
             * 为什么要/2取整，因为#占了f[i]的一半
             * 为什么累加，f(i)是以第i个元素为中心能够拓展出的最大回文半径，最多元素的回文数，回文半径内的元素也是回文数，从1个元素的回文数到f(i)个元素的回文数
             */
            result += f[i] / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution20 solution20 = new Solution20();
        System.out.println(solution20.countSubstrings2("abc"));
    }
}
