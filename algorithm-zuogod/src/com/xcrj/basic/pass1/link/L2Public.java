package com.xcrj.basic.pass1.link;
//两独立有序链表的公共部分
public class L2Public {
    public static void main(String[]args) {
        //预测结果 0 2 2 3 4 6
        int[]as={0,1,2,2,3,4,5,6};
        int[]bs={0,0,2,2,3,4,6,7,8};
        int na=as.length;
        int nb=bs.length;
        Node da=new Node();//虚伪头结点
        Node pa=da;
        Node db=new Node();//虚伪头结点
        Node pb=db;
        for (int i = 0; i < na; i++) {
            Node node=new Node(as[i],null);
            pa.next=node;
            pa=pa.next;
        }
        for (int i = 0; i < nb; i++) {
            Node node=new Node(bs[i],null);
            pb.next=node;
            pb=pb.next;
        }
        Node ha=da.next;
        Node hb=db.next;
        pa=ha;
        pb=hb;
        StringBuilder sb=new StringBuilder();
        while(pa!=null&&pb!=null){
            if(pa.val==pb.val){
                sb.append(pa.val);
                pa=pa.next;
                pb=pb.next;
                continue;
            }
            if(pa.val<pb.val){
                pa=pa.next;
            }else{
                pb=pb.next;
            }
        }
        System.out.println(sb.toString());
    }
}
