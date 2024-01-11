package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/merge-intervals/
 * 合并区间
 * 合并重叠区间
 */
public class Solution15 {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans=new LinkedList();
        //根据区间左边界正序
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int left=intervals[0][0];
        int mostRight=intervals[0][1];// 区间最大右边界
        //遍历更新最大右边界
        for(int i=1;i<intervals.length;i++){
            // 第i个区间的左边界>最大右边界，添加，更新最大右边界
            if(intervals[i][0]>mostRight){
                ans.add(new int[]{left,mostRight});
                mostRight=intervals[i][1];
                left=intervals[i][0];
            }
            // 更新最大右边界
            else{
                mostRight=Math.max(mostRight,intervals[i][1]);
            }
        }
        // 最后1个区间
        ans.add(new int[]{left,mostRight});
        return ans.toArray(new int[ans.length][]);
    }
}