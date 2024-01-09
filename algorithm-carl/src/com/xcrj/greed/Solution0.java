package com.xcrj.greed;
import java.util.Arrays;

/**
 * https://leetcode.cn/problems/assign-cookies/
 * 分发饼干
 * 饼干数组 小孩胃口数组
 * 使用饼干尽可能喂饱更多小孩
 */
public class Solution0 {
    /**
     * 思路1：优先考虑饼干，小饼干先喂饱小胃口
     * @param g 胃口
     * @param s 饼干
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans=0;
        int j=0;
        // 从小到大遍历饼干
        for(int i=0;i<s.length&&j<g.length;i++){
            // 先将小尺寸饼干满足小胃口的小孩
            if(s[i]>=g[j]){
                j++;
                ans++;
            }
        }
        return ans;
    }
    
    /**
     * 思路2：优先考虑胃口，先喂饱大胃口
     * @param g 胃口
     * @param s 饼干
     */
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans=0;
        int j=s.length-1;
        // 从大到小遍历胃口
        for(int i=g.length-1;i>=0&&j>=0;i--){
            // 先将大胃口小孩用大饼干满足
            if(s[j]>=g[i]){
                j--;
                ans++;
            }
        }
        return ans;
    }
}