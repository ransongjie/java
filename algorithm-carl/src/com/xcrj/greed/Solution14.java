package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/partition-labels/description/
 * 划分字母区间
 */
public class Solution14{
    /*
     统计每一个字符最后出现的位置
     从头遍历字符，并更新字符的最远出现下标right，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
     */
    public List<Integer> partitionLabels(String S) {
        //
        int[]cnts=new int[26];
        char[]cs=S.toCharArray();
        for(int i=0;i<cs.length;i++){
            cnts[cs[i]-'a']=i;
        }
        //
        List<Integer> ans=new LinkedList();
        int farthest=0;
        int pre=0;
        for(int i=0;i<cs.length;i++){
            farthest=Math.max(farthest,cnts[cs[i]-'a']);
            if(i==farthest){
                ans.add(farthest-pre+1);
                pre=farthest+1;// 下1个位置
            }
        }
        return ans;
    }
}