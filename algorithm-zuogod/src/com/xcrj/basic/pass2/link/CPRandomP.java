package com.xcrj.basic.pass2.link;

import java.util.HashMap;
import java.util.Map;

//深度拷贝含有随机指针的链表
public class CPRandomP {
    public static void main(String[] args) {
        NodeR h=new NodeR(1);
        NodeR n1=new NodeR(2);
        NodeR n2=new NodeR(3);
        NodeR n3=new NodeR(4);
        NodeR n4=new NodeR(5);
        h.next=n1;n1.next=n2;n2.next=n3;n3.next=n4;
        n1.rand=n3;
        n2.rand=n1;
        // NodeR newH=cpRandomP1(h);
        NodeR newH=cpRandomP2(h);

        NodeR p=newH;
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }
        System.out.println("===========");
        p=newH;
        while(p!=null){
            if(p.rand!=null){
                System.out.println(p.val+"->"+p.rand.val);
            }
            p=p.next;
        }
    }

    public static NodeR cpRandomP1(NodeR h) {
        if(h==null) return null;
        Map<NodeR,NodeR> map=new HashMap<>();

        NodeR p=h;
        while(p!=null){
            map.put(p,new NodeR(p.val));
            p=p.next;
        }

        p=h;
        while(p!=null){
            if(p.next!=null){
                map.get(p).next=map.get(p.next);
            }
            p=p.next;
        }

        p=h;
        while(p!=null){
            if(p.rand!=null){
                map.get(p).rand=map.get(p.rand);
            }
            p=p.next;
        }

        return map.get(h);
    }

    
    public static NodeR cpRandomP2(NodeR h) {
        if(h==null) return null;

        NodeR p=h;
        while(p!=null){
            NodeR n=new NodeR(p.val);
            n.next=p.next;
            p.next=n;

            p=p.next.next;
        }

        p=h;
        while(p!=null){
            if(p.rand!=null){
                p.next.rand=p.rand.next;
            }

            p=p.next;
        }

        p=h;
        NodeR newH=p.next;
        while(p.next.next!=null){
            NodeR n=p.next.next;
            p.next.next=n.next;
            p.next=n;
            p=n;
        }

        return newH;
    }
}
