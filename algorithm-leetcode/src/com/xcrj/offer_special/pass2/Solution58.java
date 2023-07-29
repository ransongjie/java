package com.xcrj.offer_special.pass2;

import java.util.*;

/**
 * 剑指 Offer II 058. 日程表
 * 时间是半开区间，即 [start, end), 实数 x 的范围为，start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 调用book()不产生重复预订返回true，产生重复预定返回false
 */
public class Solution58 {
    /**
     * 暴力 存储到list<int[]>后再遍历
     */
    class MyCalendar1 {
        // int[0] start;int[1] end
        List<int[]> list;
        public MyCalendar1() {
            list=new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for(int[] se:list){
                int s=se[0];
                int e=se[1];
                // xcrj 
                if(start<e&&s<end) return false;
            }
            list.add(new int[]{start,end});
            return true;
        }
    }

    /**
     * TreeSet(Comparator<? super E> comparator)
     */
    class MyCalendar2 {
        TreeSet<int[]> treeSet;

        public MyCalendar2() {
            treeSet=new TreeSet<>((as,bs)->as[0]-bs[0]);
        }

        public boolean book(int start, int end) {
            if(treeSet.isEmpty()) {
                treeSet.add(new int[]{start,end});
                return true;
            }

            int[] temp=new int[]{end,0};

            // end在开头
            // 插到开头
            int[] ceiling=treeSet.ceiling(temp);
            if(ceiling==treeSet.first()){
                treeSet.add(new int[]{start,end});
                return true;
            }

            // end在中间或末尾
            // 插入中间或末尾
            int[] lower=treeSet.lower(temp);
            if(lower[1]<=start){
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
            treeSet=new TreeSet<>((as,bs)->as[0]-bs[0]);
        }

        public boolean book(int start, int end) {
            if(treeSet.isEmpty()) {
                treeSet.add(new int[]{start,end});
                return true;
            }

            int[] temp=new int[]{end,0};

            int[] ceiling=treeSet.ceiling(temp);
            if(ceiling==null){
                // 插入末尾
                int[] last=treeSet.last();
                if(start>=last[1]){
                    treeSet.add(new int[]{start,end});
                    return true;
                }else return false;
            }else{
                int[] lower=treeSet.lower(temp);
                if(lower==null){
                    // 插入开头
                    treeSet.add(new int[]{start,end});
                    return true;
                }else{
                    // 插入中间
                    if(start>=lower[1]){
                        treeSet.add(new int[]{start,end});
                        return true;
                    }else return false;
                }
            }
        }
    }

    /**
     * 线段树：一段作为1个结点，每个结点有1个编号，编号记录在tree和lazy hash表中。构建树的过程类似于二分查找
     * 线段树
     * - 本质：将数组序列的一段信息综合起来
     * - 静态线段树：使用堆存储结构存储区间信息，需要提前创建堆结构
     * - 动态线段树：使用指针存储线段树，按需创建线段树结点
     * 
     * 静态线段树
     * - 初始化()：arr[0,...,10^9]初始为全0
     * - book()：预定区间[start,end)则将arr[start,...,end-1]中的每个元素都标记为1
     * 
     * 动态线段树
     * - lazy，[l,r]，区间是否已经被预定
     * - tree，[l,r]，区间是否存在标记为1的元素
     * - book()：
     * -- 查询区间是否被预定：是否可预订，判断区间[start,...,end-1]是否存在元素被标记，若存在被标记为1的元素，表明该区间不可被预定，否则可以预定，
     * -- 更新标记和线段树：标记已预订，若可以预定，则将arr[start,...,end-1]标记为1；更新线段树
     */
    class MyCalendar4 {
        // 记录线段树的结点编号
        Set<Integer> tree;
        // 懒标记，便于快速查找，标记已经预定过的区间
        Set<Integer> lazy;

        public MyCalendar4() {
            tree=new HashSet<>();
            lazy=new HashSet<>();
        }

        public boolean book(int start, int end) {
            // -1，题目要求 [start,end)
            // 线段树 结点已存在
            if(query(start, end - 1, 0, 1000000000, 1)) return false;
            // 更新 线段树 结点
            update(start, end - 1, 0, 1000000000, 1);
            return true;
        }

        private boolean query(int start, int end, int l, int r, int idx) {
            // [l,r,start,end]右侧||左侧[start,end,l,r] 
            if(end<l||start>r) return false;
            // 使用lazy标记，快速查找
            if(lazy.contains(idx)) return true;
            // [start,l,r,end] 线段树的这个结点是否已经存在
            if(start<=l&&end>=r) return tree.contains(idx);
            
            // 查询线段树子树
            int mid=(l+r)>>1;
            // [start,l,end,mid]
            // 2*idx 线段树左子结点编号
            if(mid>=end) return query(start, end, l, mid, 2*idx);
            // [mid+1,start,end,r]
            // 2*idx+1 线段树右子结点编号
            // +1 mid已经在上面被操作
            if(mid<start) return query(start, end, mid+1, r, 2*idx+1);
            // [l,start,mid,end,r]
            // 左右子树只要有一个为true即可
            return query(start, end, l, mid, 2*idx)|query(start, end, mid+1, r, 2*idx+1);
        }

        private void update(int start, int end, int l, int r, int idx) {
            // [l,r,start,end]右侧||左侧[start,end,l,r] 
            if(end<l||start>r) return;
            // [start,l,r,end] 线段树的这个结点是否已经存在
            if(start<=l&&end>=r) {
                tree.add(idx);
                lazy.add(idx);
                return;
            }

            int mid=(l+r)>>1;
            // [start,l,end,mid] 更新左子树
            update(start, end, l, mid, 2*idx);
            // [l,start,mid,end,r] 更新右子树
            // +1 mid已经在上面被操作
            update(start, end, mid+1, r, 2*idx+1);
            // [l,start,mid,end,r] 更新自己
            tree.add(idx);
        }
    }

}
