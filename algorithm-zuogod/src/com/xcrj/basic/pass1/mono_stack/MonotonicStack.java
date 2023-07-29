package com.xcrj.basic.pass1.mono_stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//单调栈。as中含有相等的值。a左右最近且大的值
public class MonotonicStack {
    public static void main(String[] args) {
        // int[] as = {8, 7, 2, 5, 4, 1, 9};
        // List<Element> els=monotonicStack(as);
        // // List<Element> els=cp(as);
        // for (Element e : els) {
        //     System.out.println("as["+e.lefti+"]="+e.leftv+", "
        //                       +"as["+e.curi+"]="+e.curv+", "
        //                       +"as["+e.righti+"]="+e.rightv);
        // }

        int times=1000000;
        int maxLen=100;
        int maxV=1000;
        for (int i = 0; i < times; i++) {
            int[] as=getAs(maxLen, maxV);
            List<Element> els1=cp(as);
            List<Element> els2=monotonicStack(as);
            for (int j = 0; j < els1.size(); j++) {
                Element e1=els1.get(j);
                Element e2=els2.get(j);
                if(e1.leftv==null&&e2.leftv==null
                || e1.rightv==null&&e2.rightv==null){
                    continue;
                }
                if(!e1.lefti.equals(e2.lefti)
                || !e1.leftv.equals(e2.leftv)
                || !e1.curi.equals(e2.curi)
                || !e1.curv.equals(e2.curv)
                || !e1.righti.equals(e2.righti)
                || !e1.rightv.equals(e2.rightv)){
                    System.out.println("not good");
                    System.out.println(Arrays.toString(as));
                    System.out.println("as["+e1.lefti+"]="+e1.leftv+", "
                                              +"as["+e1.curi+"]="+e1.curv+", "
                                              +"as["+e1.righti+"]="+e1.rightv);
                    System.out.println("as["+e2.lefti+"]="+e2.leftv+", "
                                              +"as["+e2.curi+"]="+e2.curv+", "
                                              +"as["+e2.righti+"]="+e2.rightv);
                    throw new RuntimeException();
                }
            }
        }
    }

    static class Element {
        Integer lefti;
        Integer leftv;
        Integer curi;
        Integer curv;
        Integer righti;
        Integer rightv;
    }

    public static List<Element> monotonicStack(int[] as) {
        List<Element> rls = new ArrayList<>();
        // 单调递减栈
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < as.length; i++) {
            if (stack.isEmpty()) {
                List<Integer> ls = new ArrayList<>();
                ls.add(i);
                stack.push(ls);
                continue;
            }
            if (as[stack.peek().get(0)] > as[i]) {
                List<Integer> ls = new ArrayList<>();
                ls.add(i);
                stack.push(ls);
                continue;
            }
            if (as[stack.peek().get(0)] == as[i]) {
                stack.peek().add(i);
                continue;
            }
            /**
             * popList中每个值的左近且大的值是peekList(last)的值
             */
            while (!stack.isEmpty() && as[stack.peek().get(0)] < as[i]) {
                List<Integer> ls = stack.pop();
                int pi = -1;// 可能无左近且大的数
                Integer pv = null;
                if (!stack.isEmpty()) {
                    List<Integer> pls = stack.peek();
                    pi = pls.get(pls.size() - 1);
                    pv = as[pi];
                }
                for (int j=0;j<ls.size();j++) {
                    Element e = new Element();
                    e.curi = ls.get(j);
                    e.curv = as[ls.get(j)];
                    e.lefti = pi;
                    e.leftv = pv;
                    e.righti = i;
                    e.rightv = as[i];
                    rls.add(e);
                }
            }
            //while (!stack.isEmpty() && as[stack.peek().get(0)] < as[i]) 
            // if(stack.isEmpty()){
            //     List<Integer> ls = new ArrayList<>();
            //     ls.add(i);
            //     stack.push(ls);
            //     continue;
            // }
            // if (as[stack.peek().get(0)] == as[i]){
            //     stack.peek().add(i);
            //     continue;
            // }
            // if (as[stack.peek().get(0)] > as[i]) {
            //     List<Integer> ls = new ArrayList<>();
            //     ls.add(i);
            //     stack.push(ls);
            //     continue;
            // }
            i--;//代替上面注释的部分
        }

        while (!stack.isEmpty()) {
            List<Integer> ls = stack.pop();
            int pi = -1;// 可能无左近且大的数
            Integer pv = null;
            if (!stack.isEmpty()) {
                List<Integer> pls = stack.peek();
                pi = pls.get(pls.size() - 1);
                pv = as[pi];
            }
            for (int j=0;j<ls.size();j++) {
                Element e = new Element();
                e.curi = ls.get(j);
                e.curv = as[ls.get(j)];
                e.lefti = pi;
                e.leftv = pv;
                e.righti = -1;// 一定无右近且大的数
                e.rightv = null;
                rls.add(e);
            }
        }

        rls.sort((o1,o2)->o1.curi-o2.curi);//
        return rls;
    }

    public static List<Element> cp(int[] as) {
        List<Element> rls=new ArrayList<>();
        for (int i = 0; i < as.length; i++) {
            int a=as[i];
            //往左
            Integer lefti=-1;
            Integer leftv=null;
            for (int j = i-1; j >-1 ; j--) {
                if(as[j]>a){
                    lefti=j;
                    leftv=as[j];
                    break;
                }
            }
            //往右
            Integer righti=-1;
            Integer rightv=null;
            for (int j = i+1; j < as.length; j++) {
                if(as[j]>a){
                    righti=j;
                    rightv=as[j];
                    break;
                }
            }
            //
            Element e=new Element();
            e.lefti=lefti;
            e.leftv=leftv;
            e.curi=i;
            e.curv=as[i];
            e.righti=righti;
            e.rightv=rightv;
            rls.add(e);
        }
        return rls;
    }

    public static int[] getAs(int maxLen,int maxV) {
        int len=(int)(Math.random()*maxLen);
        while(len==0){
            len=(int)(Math.random()*maxLen);
        }
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
