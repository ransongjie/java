package com.xcrj.pointer2;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 * 环形链表 II
 * 入口结点
 */
public class Main7 {
    /**
     * 2*slow=fast;
     * 2*(x+y)=x+y+(z+y)*n, 相遇在圈内
     * x+y=(z+y)*n
     * x=(z+y)*n-y=(z+y)*(n-1)+z
     * x=z, 从相遇结点开始同时往后走x步就是环入口结点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, p = head;
        // 找到相遇点
        while (fast != null && fast.next != null) { // 模板，while(前1个结点) {下1个结点}，只相差1个结点
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // slow和p同时往后走，相遇就是环入口结点
                while (slow != p) {
                    slow = slow.next;
                    p = p.next;
                }
                return p;
            }
        }

        return null;
    }
}
