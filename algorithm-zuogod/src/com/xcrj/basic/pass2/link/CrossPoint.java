package com.xcrj.basic.pass2.link;

/**
 * 返回可能有环链表的1个交点，可能没有交点
 * https://zhuanlan.zhihu.com/p/106121179
 */
public class CrossPoint {
    public static void main(String[] args) {
        //loop1=null and loop2=null
        //Y形 or 平行线
        //Y形
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // b1.next=b2;b2.next=b3;b3.next=a4;
        // System.out.println(crossPoint(a1, b1).val);
        //平行线
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // b1.next=b2;b2.next=b3;
        // Node n=crossPoint(a1, b1);
        // System.out.println(n==null?null:n.val);

        //loop1=null and loop2!=null
        //loop1!=null and loop2=null
        //不可能相交
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;a5.next=a3;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // b1.next=b2;b2.next=b3;
        // Node n=crossPoint(a1, b1);
        // System.out.println(n==null?null:n.val);

        //loop1!=null and loop2!=null
        //6形 尾巴相交 圆圈相交 两个独立的6 
        //6形尾巴相交
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;a5.next=a3;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // b1.next=b2;b2.next=b3;b3.next=a2;
        // Node n=crossPoint(a1, b1);
        // System.out.println(n==null?null:n.val);

        //6形圆圈相交
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;a5.next=a3;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // b1.next=b2;b2.next=b3;b3.next=a4;
        // Node n=crossPoint(a1, b1);
        // System.out.println(n==null?null:n.val);

        //两个独立的6
        // Node a1=new Node(1);
        // Node a2=new Node(2);
        // Node a3=new Node(3);
        // Node a4=new Node(4);
        // Node a5=new Node(5);
        // a1.next=a2;a2.next=a3;a3.next=a4;a4.next=a5;a5.next=a3;
        // Node b1=new Node(1);
        // Node b2=new Node(2);
        // Node b3=new Node(3);
        // Node b4=new Node(4);
        // Node b5=new Node(5);
        // b1.next=b2;b2.next=b3;b3.next=b4;b4.next=b5;b5.next=b3;
        // Node n=crossPoint(a1, b1);
        // System.out.println(n==null?null:n.val);
    }   

    public static Node crossPoint(Node h1,Node h2) {
        if(h1==null||h2==null) return null;
        // 获取链表环的入口结点
        Node loop1=circleIn(h1);
        Node loop2=circleIn(h2);
        /**
         * loop1=null && loop2=null》Y形 or 平行线
         * 终点结点不是同一个》平行线
         * 终点结点是同一个》Y形
         * 如何求Y形的交点？长度相减，更长的链表多走差值长度，两个链表再一起走直到相等，就是交点
         */
        if(loop1==null&&loop2==null){
            EndLen el1=getEndLen(h1);
            EndLen el2=getEndLen(h2);
            if(el1.end!=el2.end) return null;
            return getYJoint(h1, h2, el1.len, el2.len);
        }

        // (loop1=null && loop2!=null) || (loop1!=null and loop2=null)》不可能相交
        if(loop1==null|loop2==null) return null;

        // loop1!=null && loop2!=null》两个6
        if(loop1!=null&&loop2!=null){
            // loop1==loop2》两个6在尾巴上相交
            if(loop1==loop2){
                int len1=getLen(h1, loop1);
                int len2=getLen(h2, loop1);
                return getYJoint(h1,h2,len1,len2);
            }

            //遇到loop2之前遇到了loop1（遇到他之前遇到了自己）》两个独立的6 
            Node p=loop1;
            while(p!=loop2){
                p=p.next;
                if(p==loop1) return null;
            }

            // 两个6的圆圈相交，返回其中1个交点即可 
            return loop1;
        }

        return null;
    }

    public static Node circleIn(Node h) {
        if(h==null) return null;
        Node p=h;
        Node fast=h;
        Node slow=h;

        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) break;
        }
        if(fast==null||fast.next==null||fast.next.next==null){
            return null;
        }

        while(p!=slow){
            p=p.next;
            slow=slow.next;
        }

        return p;
    } 

    static class EndLen{
        int len;
        Node end;
    }

    public static EndLen getEndLen(Node h) {
        if(h==null) return null;
        Node p=h;
        int len=1;
        while(p.next!=null){
            p=p.next;
            len++;
        }
        EndLen el=new EndLen();
        el.end=p;
        el.len=len;
        return el;
    }

    public static int getLen(Node h,Node end) {
        Node p=h;
        int len=1;
        while(p!=end){
            p=p.next;
            len++;
        }
        return len;
    }

    public static Node getYJoint(Node h1,Node h2,int len1,int len2) {
        if(len1>=len2){
            int diff=len1-len2;
            Node p1=h1;
            for (int i = 0; i < diff; i++) {
                p1=p1.next;
            }
            Node p2=h2;
            while(p1!=p2){
                p1=p1.next;
                p2=p2.next;
            }
            return p1;
        }

        if(len2>len1){
            int diff=len2-len1;
            Node p2=h2;
            for (int i = 0; i < diff; i++) {
                p2=p2.next;
            }
            Node p1=h1;
            while(p1!=p2){
                p1=p1.next;
                p2=p2.next;
            }
            return p1;
        }

        return null;
    }
}
