package com.xcrj.offer_special.pass3;
/**
 * 剑指 Offer II 020. 回文子字符串的个数
 * - 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * - 字符串由小写字母构成
 */
public class Solution20 {
    /**
     * 双指针奇偶拓展
     * i是回文中心
     * 奇拓展 l=0,r=0,i=0
     * 偶拓展 l=0,r=1,i=1
     * 奇拓展 l=1,r=1,i=2
     * 偶拓展 l=1,r=2,i=3
     * ...
     * 偶拓展 l=n-2,r=n-1,i=2*n-2
     * 奇拓展 l=n-1,r=n-1,i=2*n-1
     * 
     * 规律，
     * l=下取整i/2
     * r=l+i%2
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int cnt=0;
        for(int i=0;i<2*s.length()-1;i++){
            int l=i/2;
            int r=l+i%2;
            while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
                // 中心拓展一次，产生一个新的回文串
                cnt++;
                l--;
                r++;
            }
        }
        return cnt;
    }

    /**
     * 在基础之上进行中心拓展
     * 利用历史最大回文半径进行拓展, xcrj 历史基础
     * 上面的方法，每次从回文中心都是从0开始，没有基础
     * 下面的方法，每次中回文中心都是从f[i]开始，有基础
     * 
     * 利用历史中心拓展信息
     * - 历史最大回文半径的回文中心的右端点 maxr
     * - 历史最大回文半径的回文中心 maxc
     * 
     * 使用动态规划数组f[i]=center_i_max_radius
     * - i>maxr：无法利用之前的所有中心拓展的信息。f[i]=1
     * - i<=maxr：利用i的关于maxc的对称点j f[j]。利用i到maxr的距离 rmax-i+1。f[i]=min{f[j],rmax-i+1}
     * 
     * Manacher可以在线性时间内求解 最长回文子串
     * 
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int cnt=0;
        // xcrj 此种求解方法 要求 字符串序列为奇数，s.len*2+1+1+1
        // 处理s，$#c1#,c2#,c3#!
        StringBuilder sb=new StringBuilder();
        sb.append('$').append('#');
        for(char c:s.toCharArray()){
            sb.append(c).append("#");
        }
        sb.append('!');
        // 动态规划数组，
        int[] f=new int[sb.length()-1];
        // 初始化
        int maxc=0;
        int maxr=0;
        // 开始的$和末尾的!不需要处理
        for(int i=1;i<sb.length()-1;i++){
            //xcrj 找基础 
            f[i]=i>maxr?1:Math.min(f[2*maxc-i],maxr-i+1);
            //xcrj 在基础之上进行中心拓展
            while(sb.charAt(i+f[i])==sb.charAt(i-f[i])){
                f[i]+=1;
            }
            //更新 maxr maxc
            if(i+f[i]-1>maxr){
                maxr=i+f[i]-1;
                maxc=i;
            }
            //不考虑添加的特殊字符 /2
            cnt+=f[i]/2;
        }

        return cnt;
    }
}
