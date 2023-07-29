package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer II 074. 合并区间
 * intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class Solution74 {
    /**
     * 
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if(intervals.length==0)return new int[0][2];
        // 按照左边界排序
        // 第2个入参，是第1个入参的元素
        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);

        List<int[]> list=new ArrayList<>();
        for(int[] interval:intervals){
            int nl=interval[0];
            int nr=interval[1];
            int lastr=list.get(list.size()-1)[1];
            // 已经拍过顺序，lastl<nl是一定的
            // [lastl,lastr,nl,nr]
            if(lastr<nl){
                list.add(interval);
            }
            // [lastl,nl,lastr] max(lastr,nr)做右边界
            else{
                list.get(list.size()-1)[1]=Math.max(lastr,nr);
            }
        }

        // xcrj
        return list.toArray(new int[list.size()][]);
    }
}
