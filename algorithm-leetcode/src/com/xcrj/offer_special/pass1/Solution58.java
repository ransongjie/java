package com.xcrj.offer_special.pass1;

import java.util.*;

/**
 * 剑指 Offer II 058. 日程表
 * 时间是半开区间，即 [start, end), 实数 x 的范围为，start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 调用book()不产生重复预订返回true，产生重复预定返回false
 */
public class Solution58 {

    /**
     * 存储到List<int[]>中再遍历
     */
    class MyCalendar1 {
        // ！int[0]开始时间，int[1]结束时间
        List<int[]> bookList;

        public MyCalendar1() {
            this.bookList = new ArrayList<>();
        }

        /**
         * 两个区间[s1,e1), [s2,e2)
         * - 没有交集：s2>=e1||s1>=e2
         * - 产生交集：s2<e1&&s1<e2
         */
        public boolean book(int start, int end) {
            for (int[] ones : this.bookList) {
                int s1 = ones[0];
                int e1 = ones[1];
                if (start < e1 && s1 < end) return false;
            }
            // 添加新日程
            this.bookList.add(new int[]{start, end});
            return true;
        }
    }

    /**
     * 给定时间段[start,end)，在TreeSet中找到可以插入的位置，[s1,e1)<[start,end)<[s2,e2)
     */
    class MyCalendar2 {
        TreeSet<int[]> treeSet;

        public MyCalendar2() {
            // public TreeSet(Comparator<? super E> comparator)传入比较器
            // as[0]是s1，bs[0]是s2，[s1,e1) [s2,e2)
            // 按时间段的start升序
            this.treeSet = new TreeSet<>((as, bs) -> as[0] - bs[0]);
        }

        public boolean book(int start, int end) {
            // 日程安排表为空，添加日程，返回true
            if (this.treeSet.isEmpty()) {
                this.treeSet.add(new int[]{start, end});
                return true;
            }
            // 找[start,end)的后一个时间段[s2,e2)
            int[] t2 = this.treeSet.ceiling(new int[]{end, 0});
            // 没找到[start,end)的后一个时间段[s2,e2)
            if (null == t2) {
                // [start,end)不在某个时间段之前，尝试插入到TreeSet最后treeSet.last()
                int[] tLast = this.treeSet.last();
                // [ls,le) [start,end)
                // 已知end>=ls，比较start>le即可
                if (start >= tLast[1]) {
                    this.treeSet.add(new int[]{start, end});
                    return true;
                } else return false;
            }
            // 找到[start,end)的后一个时间段[s2,e2)
            else {
                // 找[s2,e2)的前一个时间段[s1,e1)
                int[] t1 = this.treeSet.lower(t2);
                // 没找到[s2,e2)的前一个时间段[s1,e1)
                // 已知[start,end)<[s2,e2)
                if (null == t1) {
                    this.treeSet.add(new int[]{start, end});
                    return true;
                }
                // 找到[s2,e2)的前一个时间段[s1,e1)
                else {
                    // [s1,e1) [start,end)
                    // 已知[start,end)<[s2,e2)，判断[s1,e1)<[start,end)
                    if (start >= t1[1]) {
                        this.treeSet.add(new int[]{start, end});
                        return true;
                    } else return false;
                }
            }
        }
    }

    /**
     * 给定时间段[start,end)，在TreeSet中找到可以插入的位置，[s1,e1)<[start,end)<[s2,e2)
     */
    class MyCalendar3 {
        TreeSet<int[]> treeSet;

        public MyCalendar3() {
            // public TreeSet(Comparator<? super E> comparator)传入比较器
            // as[0]是s1，bs[0]是s2，[s1,e1) [s2,e2)
            // 按时间段的start升序
            this.treeSet = new TreeSet<>((as, bs) -> as[0] - bs[0]);
        }

        /**
         * 以end为界限 s1<=end<=s2找[start,end)的后一个时间段，前一个时间段
         * <p>
         * 已知：
         * - treeSet中一定有元素
         * - 没找到后一个时间段，则证明treeSet中的最后一个时间段都比end小
         * <p>
         * 分治法：！把1个元素添加到序列中间就分这三种情况
         * - 添加到开头：找到了后一个时间段，没有前一个时间段，没有则添加
         * - 添加到中间：找到了后一个时间段，存在前一个时间段，比较后添加
         * - 添加到末尾：没找到后一个时间段，一定能找到前一个时间段，比较后添加
         * <p>
         * 注意：
         * - treeSet.ceiling()是<=
         * - treeSet.lower()是<
         */
        public boolean book(int start, int end) {
            // 日程安排表为空，添加日程，返回true
            if (this.treeSet.isEmpty()) {
                this.treeSet.add(new int[]{start, end});
                return true;
            }
            // [s1,e1)<[start,end)<[s2,e2)
            int[] temp = new int[]{end, 0};
            int[] tAfter = this.treeSet.ceiling(temp);
            // [start,end)不存在前一个时间段则直接添加
            if (tAfter == this.treeSet.first()) {
                this.treeSet.add(new int[]{start, end});
                return true;
            }
            // [start,end)存在前一个时间段则找前一个时间段
            int[] tBefore = this.treeSet.lower(temp);
            if (tBefore[1] <= start) {
                this.treeSet.add(new int[]{start, end});
                return true;
            }
            return false;
        }
    }

    /**
     * 线段树
     * - 本质：将数组序列的一段信息综合起来
     * - 静态线段树：使用堆存储结构存储区间信息，需要提前创建堆结构
     * - 动态线段树：使用指针存储线段树，按需创建线段树结点
     *
     * 静态线段树
     * - 初始化()：arr[0,...,10^9]初始为全0
     * - book()：预定区间[start,end)则将arr[start,...,end-1]中的每个元素都标记为1
     * <p>
     * 动态线段树
     * - lazy标记区间[l,r]已经被预定
     * - tree记录区间[l,r]存在标记为1的元素
     * - book()：
     * -- 查询区间是否被预定：是否可预订，判断区间[start,...,end-1]是否存在元素被标记，若存在被标记为1的元素，表明该区间不可被预定，否则可以预定，
     * -- 更新标记和线段树：标记已预订，若可以预定，则将arr[start,...,end-1]标记为1；更新线段树
     */
    class MyCalendar4 {
        //记录区间[l,r]存在标记为1的元素
        Set<Integer> tree;
        //标记区间[l,r]已经被预定
        Set<Integer> lazy;

        public MyCalendar4() {
            this.tree = new HashSet<>();
            this.lazy = new HashSet<>();
        }

        public boolean book(int start, int end) {
            // 查询给定区间是否被标记（预定）
            // end-1右边开区间
            if (query(start, end - 1, 0, 1000000000, 1)) return false;
            update(start, end - 1, 0, 1000000000, 1);
            return true;
        }

        /**
         * 查询区间
         * 查询区间是否被标记（预定）
         *
         * @param start 预定区间左端点
         * @param end   预定区间右端点
         * @param l     给定区间左端点
         * @param r     给定区间右端点
         * @param idx   第idx个结点
         */
        private boolean query(int start, int end, int l, int r, int idx) {
            // 区间外：预定区间在给定区间外，没有被预定
            if (start > r || end < l) return false;
            // 区间已经被预定，返回true
            if (lazy.contains(idx)) return true;
            // 区间内：预定区间包含给定区间，查看线段树是否已包含改结点
            // [start,l,r,end]
            if (start <= l && r <= end) return tree.contains(idx);
                // 区间内：预定区间在给定区间外，查询左右孩子
            else {
                int mid = (l + r) >> 1;
                // [start,l,end,mid] 查询左孩子
                if (end <= mid) return query(start, end, l, mid, 2 * idx);
                    // [mid+1,start,end,r] 查询右孩子
                else if (start > mid) return query(start, end, mid + 1, r, 2 * idx + 1);
                    // [l,start,mid,end,r] 或运算拼接左右孩子
                else return query(start, end, l, mid, 2 * idx) | query(start, end, mid + 1, r, 2 * idx + 1);
            }
        }

        /**
         * 更新区间
         * 预定遇见未被标记（预定）则更新标记和线段树
         *
         * @param start 预定区间左端点
         * @param end   预定区间右端点
         * @param l     给定区间左端点
         * @param r     给定区间右端点
         * @param idx   第idx个结点
         */
        private void update(int start, int end, int l, int r, int idx) {
            // 区间外：预定区间在给定区间外，不用更新，直接返回
            if (start > r || end < l) return;
            // 区间内：预定区间包含给定区间，更新标记和线段树
            // [start,l,r,end]
            if (start <= l && end >= r) {
                this.lazy.add(idx);
                this.tree.add(idx);
            }
            // 区间外：预定区间在给定区间外，先更新左右孩子，再更新线段树
            else {
                int mid = (l + r) >> 1;
                // 更新左孩子
                update(start, end, l, mid, 2 * idx);
                // 更新右孩子
                update(start, end, mid + 1, r, 2 * idx + 1);
                // 更新线段树
                this.tree.add(idx);
            }
        }
    }
}
