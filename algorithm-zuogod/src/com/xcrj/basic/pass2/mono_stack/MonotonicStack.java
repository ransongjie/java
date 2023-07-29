package com.xcrj.basic.pass2.mono_stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈。as中含有相等的值。a左右最近且大的值
 * 单调递减栈 stack<List<Integer>>
 * 可以有相等元素
 * 1. 栈空 直接放入
 * 2. 栈顶元素更大 加入栈顶
 * 2. 栈顶元素相等 加入栈顶元素list
 * 3. 栈顶元素更小 peek peekNext(左近且大的元素) outStack(右近且大的元素)。必定有右，可能无左
 * 4. i不变
 * 5. goto 1
 * 6. 处理栈中剩余元素，可能无左，一定无右
 */
public class MonotonicStack {
    public static void main(String[] args) {
        int times=10000;
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
                || e1.rightv==null&&e2.rightv==null) continue;
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
        if(as==null||as.length==0) return null;
        List<Element> ls=new ArrayList<>();

        Stack<List<Integer>> stack=new Stack<>();

        for (int i = 0; i < as.length; i++) {
            if(stack.isEmpty()){
                List<Integer> list=new ArrayList<>();
                list.add(i);
                stack.push(list);
                continue;
            }

            if(as[stack.peek().get(0)]>as[i]){
                List<Integer> list=new ArrayList<>();
                list.add(i);
                stack.push(list);
                continue;
            }

            if(as[stack.peek().get(0)]==as[i]){
                stack.peek().add(i);
                continue;
            }

            while(!stack.isEmpty()&&as[stack.peek().get(0)]<as[i]){
                List<Integer> pls=stack.pop();

                Integer righti=i;
                Integer rightv=as[i];

                Integer lefti=null;
                Integer leftv=null;
                if(!stack.isEmpty()){
                    lefti=stack.peek().get(stack.peek().size()-1);
                    leftv=as[lefti];
                }

                for (Integer curi : pls) {
                    Element element=new Element();
                    element.righti=righti;
                    element.rightv=rightv;
                    element.lefti=lefti;
                    element.leftv=leftv;
                    element.curi=curi;
                    element.curv=as[curi];

                    ls.add(element);
                }
            }

            i--;
        }

        while(!stack.isEmpty()){
            Integer righti=null;
            Integer rightv=null;

            List<Integer> list=stack.pop();

            Integer lefti=null;
            Integer leftv=null;
            if(!stack.isEmpty()){
                lefti=stack.peek().get(stack.peek().size()-1);
                leftv=as[lefti];
            }

            for (Integer curi : list) {
                Element element=new Element();
                element.righti=righti;
                element.rightv=rightv;
                element.lefti=lefti;
                element.leftv=leftv;
                element.curi=curi;
                element.curv=as[curi];
                ls.add(element);
            }
        }

        ls.sort((o1,o2)->o1.curi-o2.curi);
        return ls;
    }

    public static List<Element> cp(int[]as) {
        List<Element> rls=new ArrayList<>();

        for (int i = 0; i < as.length; i++) {
            int a=as[i];
            //往左
            Integer lefti=null;
            Integer leftv=null;
            for (int j = i-1; j >-1 ; j--) {
                if(as[j]>a){
                    lefti=j;
                    leftv=as[j];
                    break;
                }
            }
            //往右
            Integer righti=null;
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
        int len=(int)(Math.random()*(maxLen-1)+1);
        int[]as=new int[len];
        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV);
            as[i]=v;
        }
        return as;
    }
}
