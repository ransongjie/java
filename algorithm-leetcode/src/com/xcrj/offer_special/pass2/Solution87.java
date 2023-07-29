package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 剑指 Offer II 087. 复原 IP
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 */
public class Solution87 {
    // ipv4总共有4段
    static final int SEGMENT_NUM = 4;
    int[] segments = new int[SEGMENT_NUM];
    List<String> rs = new ArrayList<>();

    // 回溯法
    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return rs;
    }

    private void dfs(String s, int segmentI, int sI) {
        // xcrj 获得结果，遍历完所有字符串s&&获得4段IP
        if(segmentI==SEGMENT_NUM&&sI==s.length()){
            // segments转储到rs中
            String r=Arrays.stream(segments).boxed().map(Object::toString).collect(Collectors.joining("."));
            rs.add(r);
            return;
        }

        // 不满足条件
        if(segmentI==SEGMENT_NUM&&sI<s.length())return;
        if(segmentI<SEGMENT_NUM&&sI==s.length())return;

        // xcrj 特殊情况，处理前导0，开始为0后面都得为0
        if(s.charAt(sI)=='0'){
            // xcrj 不是segments[sI]
            segments[segmentI]=0;
            dfs(s, segmentI+1, sI+1);
        }

        // xcrj 一般情况，获取这一段IP的值
        int addr=0;
        for(int i=sI;i<s.length();i++){
            addr=addr*10+(s.charAt(i)-'0');
            if(addr>0&&addr<=0xFF){
                segments[segmentI]=addr;
                dfs(s,segmentI+1,i+1);
            }else break;
        }
    }
}
