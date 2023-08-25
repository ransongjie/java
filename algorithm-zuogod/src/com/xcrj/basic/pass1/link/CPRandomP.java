package com.xcrj.basic.pass1.link;

import java.util.HashMap;
import java.util.Map;

//深度拷贝含有随机指针的链表
public class CPRandomP {
    public static void main(String[]args) {
        NodeR n1=new NodeR(1);
        NodeR head=n1;
        NodeR n2=new NodeR(2);
        NodeR n3=new NodeR(3);
        NodeR n4=new NodeR(4);
        NodeR n5=new NodeR(5);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n1.rand=n3;
        n2.rand=n1;
        n3.rand=n5;

         NodeR cpHead=copy1(head);
//        NodeR cpHead=copy2(head);

        NodeR q=cpHead;
        StringBuilder sb=new StringBuilder();
        while(q!=null){
            sb.append(q.val);
            q=q.next;
        }
        System.out.println(sb.toString());

        q=cpHead;
        sb=new StringBuilder();
        while(q!=null){
            if(q.rand!=null){
                sb.append(q.val);
                sb.append(q.rand.val);
            }
            q=q.next;
        }
        System.out.println(sb.toString());
    }

    //面试
    public static NodeR copy2(NodeR head) {
        NodeR p=head;
        while(p!=null){//插入结点
            NodeR p2=new NodeR(p.val,null);
            p2.next=p.next;
            p.next=p2;
            p=p.next.next;
        }

        p=head;
        while(p!=null){
            if(p.rand!=null){
                NodeR p2=p.next;
                NodeR p2rand=p.rand.next;
                p2.rand=p2rand;
            }
            p=p.next.next;//
        }
        
        p=head;
        NodeR cpHead=head.next;
        NodeR p2=cpHead;
        while(p.next.next!=null){
            NodeR pnxt=p.next.next;
            NodeR p2nxt=p2.next.next;
            p.next=pnxt;
            p=p.next;
            p2.next=p2nxt;
            p2=p2.next;
        }

        return cpHead;
    }

    //笔试
    public static NodeR copy1(NodeR head) {
        if(head==null) return null;

        Map<NodeR,NodeR> map=new HashMap<>();
        NodeR cpHead=new NodeR(head.val);
        map.put(head, cpHead);
        NodeR p=head.next;
        while(p!=null){
            NodeR node=new NodeR(p.val);
            map.put(p, node);
            p=p.next;
        }

        p=head;
        NodeR q=cpHead;
        while(p.next!=null){//q每次和下一个结点相连
            NodeR nxt=map.get(p.next);
            q.next=nxt;
            q=q.next;
            p=p.next;
        }

        p=head;
        q=cpHead;
        while(p!=null){//p遍历链表连接随机指针
            if(p.rand!=null){
                NodeR mr=map.get(p.rand);
                q.rand=mr;
            }
            p=p.next;
            q=q.next;
        }

        return cpHead;
    }
}
