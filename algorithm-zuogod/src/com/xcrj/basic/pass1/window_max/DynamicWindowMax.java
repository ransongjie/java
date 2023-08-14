package com.xcrj.basic.pass1.window_max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//动态窗口内的最大值
public class DynamicWindowMax {
    public static void main(String[] args) {
         int[]as={6,1,1,1,5};
         List<Integer> ls=windowMax(as, 3);
//         List<Integer> ls=cp(as, 3);
         System.out.println(ls);

//        int times=1000000;
//        int maxLen=100;
//        int maxV=100;
//        for (int i = 0; i < times; i++) {
//            Element e=getElement(maxLen, maxV);
//            List<Integer> ls1=cp(e.as, e.w);
//            List<Integer> ls2=windowMax(e.as, e.w);
//            if(ls1==null&&ls2==null) continue;
//            Integer[] rs1=ls1.toArray(new Integer[]{});//
//            Integer[] rs2=ls2.toArray(new Integer[]{});
//            if(!Arrays.equals(rs1, rs2)){
//                System.out.println("not good");
//                System.out.println(Arrays.toString(rs1));
//                System.out.println(Arrays.toString(rs2));
//            }
//        }
    }

    public static List<Integer> windowMax(int[]as,int w) {
        if(as==null||as.length==0||as.length<w) return null;
        List<Integer> ls=new ArrayList<>();
        DynamicWindowMax dwm=new DynamicWindowMax(as);
        for(int i=0;i<w;i++){//
            dwm.addRight();
        }
        for(int i=0;i<as.length-w+1;i++){//
            ls.add(dwm.getWindowMax());
            dwm.delLeft();
            dwm.addRight();
        }
        return ls;
    }

    int l;
    int r;
    int[]as;
    Deque<Integer> deque;//放下标

    public DynamicWindowMax(int[]as){
        l=-1;
        r=0;//
        this.as=as;
        deque=new LinkedList<>();
    }

    //右移一个
    public void addRight() {
        if(r==as.length) return;
        //从左往右严格单调递减
        while(!deque.isEmpty()&&as[deque.peekLast()]<=as[r]){
            deque.pollLast();
        }
        deque.offerLast(r++);
    }

    //左移一个
    public void delLeft(){
        if(l>r-1) return;
        l++;
        if(l==deque.peekFirst()){
            deque.pollFirst();
        }
    }

    public Integer getWindowMax() {
        if(deque.isEmpty()) return null;
        return as[deque.peekFirst()];
    }

    static class Element{
        int[] as;
        int w;
    }

    public static Element getElement(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        while(len==0){
            len=(int)(Math.random()*maxLen);
        }
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        
        int w=(int)(Math.random()*maxLen);
        while(w==0){
            w=(int)(Math.random()*maxLen);
        }

        Element e=new Element();
        e.as=as;
        e.w=w;
        return e;
    }

    public static List<Integer> cp(int[] as,int w) {
        if(as==null||as.length==0||as.length<w) return null;
        List<Integer> ls=new ArrayList<>();
        int l=0;
        int r=0;
        while(r<=as.length){
            if(r<w){
                r++;
                continue;
            }
            int[] bs=Arrays.copyOfRange(as, l, r);
            int wmax=Arrays.stream(bs).max().getAsInt();
            ls.add(wmax);
            r++;
            l++;
        }
        return ls;
    }
}
