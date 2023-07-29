package com.xcrj.company.pass1;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * k个升序数组的最窄包含区间
 */
public class A030SamlleastRangeCovering {
    static class Node{
        int val;//本身的值
        int arrId;//来源数组
        int idx;//位置来源数组中的索引
        Node(int v,int arrId,int idx){
            this.val=v;
            this.arrId=arrId;
            this.idx=idx;
        }
    }

    //自定义比较器
    static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node a1, Node a2) {
            //！！元素值相等按照来源数组编号大小排序。元素值相等时，优先使用上面的（编号小的）list
            //元素值不等按照元素值大小排序
            return a1.val!=a2.val?a1.val-a2.val:a1.arrId-a2.arrId;
        }
    }

    /**
     *
     * @param nums
     * @return [a,b]
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        //把每个递增有序链表的第1个元素放入有序表
        int n=nums.size();
        TreeSet<Node> orderTable=new TreeSet<>(new NodeComparator());
        for (int i = 0; i < n; i++) {
            orderTable.add(new Node(nums.get(i).get(0),i,0));
        }
        //
        boolean flag=false;//开关变量
        int a=0,b=0;
        //需要凑齐，每个list中都需要取1个值，只要有1个list无值可去就退出
        while(orderTable.size()==n){
            Node min=orderTable.first();
            Node max=orderTable.last();
            //！！ 比较宽度
            if(!flag||(max.val-min.val)<b-a){
                b=max.val;
                a=min.val;
                flag=true;
            }
            //最小值出有序表，入来源list的下一个元素
            min=orderTable.pollFirst();
            int idx=min.idx+1;
            //防止越界
            if(idx!=nums.get(min.arrId).size()){
                //！！ 比较器比较
                orderTable.add(new Node(nums.get(min.arrId).get(idx),min.arrId,idx));
            }
        }

        return new int[]{a,b};
    }
}
