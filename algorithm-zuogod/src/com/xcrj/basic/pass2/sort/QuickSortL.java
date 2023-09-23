package com.xcrj.basic.pass2.sort;

/**
 * 快速排序链表实现
 */
public class QuickSortL {
    /**
     * 思路，l和r指针遍历链表，swap值
     *
     * @param begin
     * @param end
     * @return
     */
    public static ListNode quickSort(ListNode begin, ListNode end) {
        // 判断为空，判断是不是只有一个节点
        if (begin == null || end == null || begin == end)
            return begin;

        //
        ListNode l = begin;
        ListNode r = begin.next;

        // l结点值作轴值
        int midVal = l.val;

        // r从l.next到end，将<midVal的结点的值交换，swap(l,r)。l是<midVal的值的左边界
        while (r != end.next && r != null) {
            // 轴值 右侧的值要大于左侧的值
            if (r.val < midVal) {
                l = l.next;
                if (l != r)
                    swap(l, r);
            }
            r = r.next;
        }

        if (begin != l)
            swap(begin, r);

        // 递归
        quickSort(begin, l);
        quickSort(l.next, end);

        return begin;
    }

    private static void swap(ListNode p, ListNode q) {
        int tmp = p.val;
        p.val = q.val;
        q.val = tmp;
    }
}
