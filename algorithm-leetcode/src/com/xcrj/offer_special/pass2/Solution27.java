package com.xcrj.offer_special.pass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 剑指 Offer II 027. 回文链表
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * 
 * xcrj 设计链表的通用做法
 * - 转储到链表
 * - 栈
 * - 递归
 */
public class Solution27 {
    /**
     * 转储值到链表
     * 再使用双指针
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> list=new ArrayList<>();
        ListNode p=head;
        while(null!=p){
            list.add(p.val);
            p=p.next;
        }

        int i=0;
        int j=list.size()-1;
        while(i<j){
            if(list.get(i)==list.get(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 转储到栈中
     * 链表和栈判断回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        int count=0;
        ListNode p=head;
        while(null!=p){
            stack.push(p.val);
            p=p.next;
            count++;
        }

        p=head;
        // 比较1半
        int half=0;
        while(count/2!=half&&!stack.isEmpty()&&null!=p){
            if(p.val==stack.pop()){
                p=p.next;
            }else{
                return false;
            }
        }
        return true;
    }
}
