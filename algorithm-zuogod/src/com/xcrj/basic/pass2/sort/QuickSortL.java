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

        //左边界
        ListNode lb = begin;
        ListNode pi = begin.next;

        // lb结点值作轴值
        int pivotVal = lb.val;

        // pi从lb.next到end，将<pivotVal的结点的值交换，swap(l,r)。l是<pivotVal的值的左边界
        while (pi != end.next && pi != null) {
            // 轴值 右侧的值要大于左侧的值
            if (pi.val < pivotVal) {
                lb = lb.next;
                if (lb != pi)
                    swap(lb, pi);
            }
            pi = pi.next;
        }

        if (begin != lb)
            swap(begin, pi);

        // 确定轴值后继续递归
        quickSort(begin, lb);
        quickSort(lb.next, end);

        return begin;
    }

    // 交换值
    private static void swap(ListNode p, ListNode q) {
        int tmp = p.val;
        p.val = q.val;
        q.val = tmp;
    }
}
