package com.xcrj.greed;
/*
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 * 根据身高重建队列
 */
public class Solution11{
    /*
     * 根据身高从高到低排序，身高相等按k从小到大排序
     * 遍历排序后的链表，将元素插入到第k个位置
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->{
            if(a[0]==b[0]) return a[1]-b[1];
            return b[0]-a[0];
        });

        List<int[]> list=new LinkedList();
        for(int[]p:people){
            list.add(p[1],p);
        }
        return list.toArray(new int[people.length][]);
    }
}