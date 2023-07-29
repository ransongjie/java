package com.xcrj.offer_special.pass3;

import java.util.*;

/**
 * 剑指 Offer II 058. 日程表
 * 时间是半开区间，即 [start, end), 实数 x 的范围为，start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 调用book()不产生重复预订返回true，产生重复预定返回false
 */
public class Solution58 {
    /**
     * 存储到list<int[]>后再遍历
     */
    class MyCalendar {
        List<int[]> list;
        public MyCalendar() {
            this.list=new ArrayList<>();
        }

        public boolean book(int start, int end) {
            // 是否冲突
            for(int[] se:list){
                int s=se[0];
                int e=se[1];
                /**
                 * [s1,e1)和[s2,e2)
                 * 不冲突：s1>=e2||s2>=e1
                 * 冲突：s1<e2&&s2<e1
                 */
                if(start<e&&s<end) return false;
            }
            // xcrj 加入日程
            list.add(new int[]{start,end});
            //
            return true;
        }
    }

    /**
     * TreeSet(Comparator<? super E> comparator)
     */
    class MyCalendar2 {
        TreeSet<int[]> treeSet;

        public MyCalendar2() {
            // 根据start升序
            this.treeSet=new TreeSet<>((as,bs)->as[0]-bs[0]);
        }

        public boolean book(int start, int end) {
            // treeSet为空
            if(treeSet.isEmpty()){
                treeSet.add(new int[]{start,end});
                return true;
            }

            // xcrj
            int[] temp={end,0};

            // 开头： [[start,end),[treeStart,treeEnd),[),...,[)]
            int[] ceiling=treeSet.ceiling(temp);
            int[] first=treeSet.first();
            if(ceiling==first){
                treeSet.add(new int[]{start,end});
                return true;
            }

            // 后面：[[treeStart,treeEnd),[start,end),[),...,[)]
            //      [[treeStart,treeEnd),[),...,[),[start,end)]
            // [lowerStart,lowerEnd), [end,0)
            int[] lower=treeSet.lower(temp);
            int lowerEnd=lower[1];
            if(lowerEnd<=start){
                treeSet.add(new int[]{start,end});
                return true;
            }

            return false;
        }
    }

    /**
     * TreeSet(Comparator<? super E> comparator)
     */
    class MyCalendar3 {
        TreeSet<int[]> treeSet;

        public MyCalendar3() {
            // 根据start升序
            this.treeSet=new TreeSet<>((as,bs)->as[0]-bs[0]);
        }

        public boolean book(int start, int end) {
            // treeSet为空
            if(treeSet.isEmpty()){
                treeSet.add(new int[]{start,end});
                return true;
            }

            // xcrj
            int[] temp={end,0};

            // 
            int[] ceiling=treeSet.ceiling(temp);
            // end>lastStart 与最后一个结点比较 [[treeStart,treeEnd),[),...,[lastStart,lastEnd)] [start,end)
            if(ceiling==null){
                int[] last=treeSet.last();
                int lastEnd=last[1];
                if(lastEnd<=start){
                    treeSet.add(new int[]{start,end});
                    return true;
                }else{
                    return false;
                }
            }else{
                int[] lower=treeSet.lower(temp);
                // end<firstStart 与第一个结点比较 [start,end) [[firstStart,firstEnd),[),...,[lastStart,lastEnd)]
                if(lower==null){
                    treeSet.add(new int[]{start,end});
                    return true;
                }
                // end>lowerStart 与中间结点比较 [[),...,[lowerStart,lowerEnd),[start,end),[),...,[lastStart,lastEnd)]
                else{
                    int lowerEnd=lower[1];
                    if(lowerEnd<=start){
                        treeSet.add(new int[]{start,end});
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
    }

    /**
     * 动态线段树,一直左右划分[0,1000000000],直到[start,end)包含子线段(l,r)
     * [start,end) 当成一个线段 [0, 1000000000] 当成总的线段
     * 最后建立的是一棵二分查找线段树
     */
    static class MyCalendar4 {
        // 记录线段树的结点编号
        Set<Integer> tree;
        // 懒标记，标记已经预定过的区间，便于快速查找
        Set<Integer> lazy;

        public MyCalendar4() {
            this.tree=new HashSet<>();
            this.lazy=new HashSet<>();
        }

        public boolean book(int start, int end) {
            //查询是否已经被预定，从编号1开始查找
            if(query(start, end-1, 0, 1000000000, 1)) return false;
            //没有被预定过，则将 [start,end) 插入到合适的位置，从编号1开始寻找合适的插入位置
            update(start,end-1,0,1000000000,1);
            return true;
        }

        private boolean query(int start, int end, int l, int r, int idx) {
            // l,r,start,end 右侧||左侧 start,end,l,r
            if(start>r||end<l) return false;
            // 使用lazy标记，快速查找
            if(lazy.contains(idx)) return true;
            // start,l,r,end 线段树的这个结点是否已经存在
            if(start<=l&&end>=r) return tree.contains(idx);

            //
            int mid=(l+r)>>1;
            // l,start,end,mid
            if(end<=mid) return query(start, end, l, mid, idx*2);
            // mid,start,end,r
            if(start>mid) return query(start, end, mid+1, r, idx*2+1);
            // l,start,mid,end,r
            boolean lb=query(start, end, l, mid, idx*2);
            boolean rb=query(start, end, mid+1, r, idx*2+1);
            return lb||rb;
        }

        private void update(int start, int end, int l, int r, int idx) {
            // [l,r,start,end]右侧||左侧[start,end,l,r] 
            if(start>r||end<l) return;
            // [start,l,r,end] 找到要插入的位置
            if(start<=l&&end>=r){
                tree.add(idx);
                lazy.add(idx);
                return;
            }

            //
            int mid=(l+r)>>1;
            // l,start,end,mid
            update(start, end, l, mid, idx*2);
            // mid,start,end,r
            update(start, end, mid+1, r, idx*2+1);
            // 父线段
            tree.add(idx);

        }
    }

    public static void main(String[] args) {
        MyCalendar4 myCalendar4=new MyCalendar4();
        int[][] ses={{10,20},{15,25},{20,30}};
        for(int[] se:ses){
            boolean r=myCalendar4.book(se[0], se[1]);
            System.out.println(r);
        }
    }
}
