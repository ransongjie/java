package com.xcrj.basic.pass1.greed;

import java.util.ArrayList;
import java.util.List;

//日程安排
public class Schedule {
    static class Program{
        int start;
        int end;
        public Program(int start,int end){
            this.start=start;
            this.end=end;
        }
    }

    public static void main(String[]args) {
        // List<Program> pls=new ArrayList<>();
        // Program p1=new Program(0, 1);
        // Program p2=new Program(0, 2);
        // Program p3=new Program(2, 4);
        // Program p4=new Program(1, 4);
        // Program p5=new Program(4, 5);
        // Program p6=new Program(2, 3);
        // pls.add(p1);pls.add(p2);pls.add(p3);pls.add(p4);pls.add(p5);pls.add(p6);
        // List<Program> rs=schedule(pls);
        // rs.stream().forEach(o->{System.out.println(o.start+", "+o.end);});

        int times=10;
        int maxLen=10;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            List<Program> pls=getPs(maxLen, maxV);
            List<Program> rs=schedule(pls);
            System.out.println(rs.size());
            rs.stream().forEach(o->{System.out.print(o.start+", "+o.end+"->");});
        }
    }

    public static List<Program> schedule(List<Program> pls) {
        if(pls==null||pls.size()==0) return null;
        List<Program> rs=new ArrayList<>();
        //
        pls.sort((o1,o2)->o1.end-o2.end);
        //
        int preEnd=pls.get(0).end;
        rs.add(pls.get(0));
        for (int i = 1; i < pls.size(); i++) {
            if(preEnd>pls.get(i).start) continue;
            rs.add(pls.get(i));
            preEnd=pls.get(i).end;
        }
        //
        return rs;
    }

    /**
     * 
     * @param maxLen
     * @param maxV 0~maxV a<b
     * @return size>0
     */
    public static List<Program> getPs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        while(len<=0){
            len=(int)(Math.random()*maxLen);
        }
        List<Program> rs=new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            int start=(int)(Math.random()*maxV);
            while(start<0){
                start=(int)(Math.random()*maxV);
            }
            int end=(int)(Math.random()*maxV);
            while(end<start){
                end=(int)(Math.random()*maxV);
            }
            Program p=new Program(start, end);
            rs.add(p);
        }
        return rs;
    }

    //todo 排列
}
