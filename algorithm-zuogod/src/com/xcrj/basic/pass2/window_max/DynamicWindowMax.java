package com.xcrj.basic.pass2.window_max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 动态窗口中的最大值
 * 双端队列
 * - 模拟窗口向右滑动，右对头入，左对头出
 * - 从左往右严格单调递减
 * - 左对头一定是最大值
 */
public class DynamicWindowMax {
    public static void main(String[] args) {
        int times=1000000;
        int maxLen=100;
        int maxV=100;
        for (int i = 0; i < times; i++) {
            Element e=getElement(maxLen, maxV);
            List<Integer> ls1=cp(e.as, e.w);
            List<Integer> ls2=dynamicWindowMax(e.as, e.w);
            if(ls1==null&&ls2==null) continue;
            Integer[] rs1=ls1.toArray(new Integer[]{});//
            Integer[] rs2=ls2.toArray(new Integer[]{});
            if(!Arrays.equals(rs1, rs2)){
                System.out.println("not good");
                System.out.println(Arrays.toString(rs1));
                System.out.println(Arrays.toString(rs2));
                throw new RuntimeException();
            }
        }
    }

    /**
     * 
     * @param as
     * @param w 窗口大小
     * @return
     */
    public static List<Integer> dynamicWindowMax(int[]as,int w) {
        if(as==null||as.length==0||as.length<w) return null;
        List<Integer> ls=new ArrayList<>();
        
        DynamicWindowMax dwm=new DynamicWindowMax(as);

        for (int i = 0; i < w; i++) {
            dwm.addRight();
        }

        for (int i = w; i < as.length+1; i++) {//
            ls.add(dwm.getWindowMax());
            dwm.addRight();
            dwm.delLeft();
        }

        return ls;
    }

    int l;
    int r;
    int[]as;
    Deque<Integer> deque;//存储as下标

    public DynamicWindowMax(int[]as) {
        this.l=-1;//
        this.r=0;
        this.as=as;
        this.deque=new LinkedList<>();
    }

    /**
     * as[r]从右侧放入deque中，保持严格单调递减
     */
    public void addRight() {
        if(r==as.length) return;

        while(!deque.isEmpty()&&as[deque.peekLast()]<=as[r]){
            deque.pollLast();
        }

        deque.offerLast(r++);
    }

    /**
     * 左对头和l相等才从左出队1个
     */
    public void delLeft() {
        if(l>r-1) return;
        l++;//
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
        int len=(int)(Math.random()*(maxLen-1)+1);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        
        int w=(int)(Math.random()*(maxLen-1)+1);

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
