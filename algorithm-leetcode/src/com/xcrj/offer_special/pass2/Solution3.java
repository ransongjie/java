package com.xcrj.offer_special.pass2;

/**
 * 剑指 Offer II 003. 前n个数字二进制中1的个数
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 * <p>
 * 考点：
 * 发现规律（动态规划）
 * r&1奇偶特性：r为奇数结果为1，r为偶数结果为0
 * r>>1奇偶特性：r为奇数或偶数结果相等，结果都是除以2取整
 */
public class Solution3 {
    /**
     * 动态规划+位运算
     * dp[2k+1]-1=dp[2k]，dp[2k+1]=dp[2k]+1=dp[k]+1：奇数的二进制表示中1的个数 比 前一个数（偶数）的二进制表示中1的个数多1，多了二进制表示末尾的1
     * dp[2k]=dp[k]：偶数的一半的二进制表示中1的个数 等于 偶数的二进制表示中1的个数，偶数右移1位只是把二进制表示末尾的0移出去了
     */
    public int[] countBits1(int n) {
        // 从0开始所以n+1
        int[] rs=new int[n+1];
        // 初始状态
        rs[0]=0;
        for(int i=1;i<=n;i++){
            // dp[2k]=dp[k]
            if(0==i%2){
                rs[i]=rs[i>>1];
            }else{
                // dp[2k]=dp[k]
                // rs[i]=rs[i-1]+1，rs[i-1]=rs[i>>1]
                rs[i]=rs[i>>1]+1;
            }
        }
        return rs;
    }

    /**
     * 去掉奇偶判断
     * 当i是偶数，加0，加r&1
     * 当i是奇数，加1，加r&1
     */
    public int[] countBits2(int n) {
        // 从0开始所以n+1
        int[] rs=new int[n+1];
        // 初始状态
        rs[0]=0;
        for(int i=1;i<=n;i++){
            rs[i]=rs[i>>1]+(i&1);
        }
        return rs;
    }
}
