package com.xcrj.basic.pass1.link;

//返回可能有环链表的1个交点，可能没有交点
public class CrossPoint {
    public static void main(String[] args) {
        Node node100=new Node(100);
        Node node101=new Node(101);
        Node node102=new Node(102);
        Node node103=new Node(103);
        node100.next=node101;
        node101.next=node102;
        node102.next=node103;
        // node103.next=node100;

        Node node10=new Node(10);
        Node node11=new Node(11);
        Node node12=new Node(12);
        node10.next=node11;
        node11.next=node12;
        // node12.next=node103;
        node12.next=node11;
        // node12.next=node100;
        // node103.next=node101;//
        Node head1=node10;

        Node node20=new Node(20);
        Node node21=new Node(21);
        Node node22=new Node(22);
        Node node23=new Node(23);
        Node node24=new Node(24);
        node20.next=node21;
        node21.next=node22;
        node22.next=node23;
        node23.next=node24;
        // node24.next=node101;
        node24.next=node22;
        // node24.next=node100;
        // node103.next=node101;//
        Node head2=node20;

        Node c=cross(head1, head2);
        System.out.println(c==null?null:c.val);
    }

    public static Node cross(Node head1,Node head2) {
        if(head1==null||head2==null) return null;
        Node loop1=circle(head1);
        Node loop2=circle(head2);
        //都无环 Y形相交 或者 不想交
        if(loop1==null&&loop2==null){
            Two t1=lenEnd(head1);
            Two t2=lenEnd(head2);
            if(t1.end!=t2.end){
                return null;
            }
            return yCross(t1.len, t2.len, head1, head2);
        }
        //其中1个有环 不可能相交
        if(loop1!=null^loop2!=null) return null;

        //两个都有环 且环入口相等 变成第1种 Y形
        if(loop1!=null&&loop2!=null&&loop1==loop2){
            int len1=len(head1,loop1);
            int len2=len(head2,loop2);
            return yCross(len1, len2, head1, head2);
        } 

        //两个都有环 且loop1继续可以遇到loop2 loop1和loop2都是交点
        //两个都有环 且loop1继续不可以遇到loop2 没有交点
        if(loop1!=null&&loop2!=null){
            Node p=loop1.next;
            while(p!=null){
                if(p==loop2){
                    return loop1;
                }
                if(p==loop1){//每碰到loop2
                    break;
                }
                p=p.next;
            }
            return null;
        }

        return null;
    }

    public static Node circle(Node head) {
        if(head==null) return null;
        if(head.next==null) return null;
        Node slow=head;
        Node fast=head;
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==null) return null;
            if(slow==fast) break;
        }
        if(fast.next==null) return null;

        Node p=head;
        while(p!=slow){
            p=p.next;
            slow=slow.next;
        }
        return p;
    }

    public static Two lenEnd(Node head) {
        int len=0;
        Node end=head;
        Node p=head;
        while(p!=null){
            len++;
            end=p;
            p=p.next;
        }
        return new Two(len,end);
    }

    public static int len(Node head,Node end) {
        int len=0;
        Node p=head;
        while(p!=null){
            len++;
            if(p==end){
                break;
            }
            p=p.next;
        }
        return len;
    }

    static class Two{
        int len;
        Node end;
        public Two() {
            
        }
        public Two(int len,Node end) {
            this.len=len;
            this.end=end;
        }
    }

    public static Node yCross(int len1,int len2,Node head1,Node head2) {
        if(head1==null||head2==null) return null;
        int k=len1-len2;
        Node p1=head1;
        Node p2=head2;
        if(k>=0){
            for (int i = 0; i < k; i++) {
                p1=p1.next;
            }
        }else{
            for (int i = 0; i < -k; i++) {
                p2=p2.next;
            }
        }

        while(p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }

        return p1;
    }
}
