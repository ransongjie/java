package com.xcrj.basic.pass2.mono_stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 正数数组中 累积和*最小值=A 求A_max
 */
public class CumMin {
    public static void main(String[] args) {
        // int[]as={7,1,7};
        // // int max1=cumMin(as);
        // // int max2=cp(as);
        // // System.out.println(max1);
        // // System.out.println(max2);
        // List<We> ls1=getLeftRight(as);
        // List<We> ls2=getLeftRight2(as);
        // System.out.println();

        int times=10000;
        int maxL=100;
        int maxV=10;
        for (int i = 0; i < times; i++) {
            int[]as=getAs(maxL, maxV);
            int maxCP=cp(as);
            int max=cumMin(as);
            if(maxCP!=max){
                System.out.println(Arrays.toString(as));
                System.out.println(maxCP);
                System.out.println(max);
                throw new RuntimeException();
            }
        }
    }

    public static int cumMin(int[]as) {
        if(as==null||as.length==0) return 0;

        List<We> ls=getLeftRight(as);
        int max=Integer.MIN_VALUE;
        for (We we : ls) {
            int sum=getSum(as,we);
            max=Math.max(max, sum*as[we.cur]);
        }

        return max;
    }

    /**
     * 左右不空 (left,right)
     * 左侧为空 [cur,right) 
     * 右侧为空 (left,cur]
     * 左右都空 cur
     * 
     * @param as
     * @param we
     * @return
     */
    private static int getSum(int[]as,We we){
        int sum=0;

        if(we.left==null&&we.right==null){
            sum=as[we.cur];
            return sum;
        }
        
        if(we.left==null&&we.right!=null){
            for (int i = we.cur; i < we.right; i++) {
                sum+=as[i];
            }
            return sum;
        }

        if(we.left!=null&&we.right==null){
            for (int i = we.left+1; i <= we.cur; i++) {
                sum+=as[i];
            }
            return sum;
        }
        
        if(we.left!=null&&we.right!=null){
            for (int i = we.left+1; i < we.right; i++) {
                sum+=as[i];
            }
            return sum;
        }
        
        return sum;
    }

    static class We{
        Integer left;
        Integer right;
        Integer cur;
    }

    private static List<We> getLeftRight(int[]as) {
        if(as==null||as.length==0) return null;

        List<We> ls=new ArrayList<>();

        //严格单调递减栈
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

            //一定有右，可能无左
            while(!stack.isEmpty()&&as[stack.peek().get(0)]<as[i]){
                Integer right=i;

                List<Integer> pls=stack.pop();

                Integer left=null;
                if(!stack.isEmpty()){
                    left=stack.peek().get(stack.peek().size()-1);
                }

                for (Integer cur : pls) {
                    We we=new We();
                    we.right=right;
                    we.left=left;
                    we.cur=cur;
                    ls.add(we);
                }
            }

            i--;
        }

        //处理栈中剩余元素
        //一定无右，可能有左
        Integer right=null;
        while(!stack.isEmpty()){
            List<Integer> list=stack.pop();
            
            Integer left=null;
            if(!stack.isEmpty()){
                left=stack.peek().get(stack.peek().size()-1);
            }

            for (Integer cur : list) {
                We we=new We();
                we.right=right;
                we.left=left;
                we.cur=cur;
                ls.add(we);
            }
        }

        return ls;
    }

    public static int[] getAs(int maxL,int maxV) {
        int len=(int)(Math.random()*(maxL-1)+1);
        int[]as=new int[len];

        for (int i = 0; i < len; i++) {
            int v=(int)(Math.random()*maxV+1);
            as[i]=v;
        }

        return as;
    }

    public static int cp(int[]as) {
        if(as==null||as.length==0) return 0;

        List<We> ls=getLeftRight2(as);
        int max=Integer.MIN_VALUE;
        for (We we : ls) {
            int sum=getSum(as,we);
            max=Math.max(max, sum*as[we.cur]);
        }

        return max;
    }

    private static List<We> getLeftRight2(int[]as) {
        List<We> rls=new ArrayList<>();

        for (int i = 0; i < as.length; i++) {
            int a=as[i];
            //往左
            Integer lefti=null;
            for (int j = i-1; j >-1 ; j--) {
                if(as[j]>a){
                    lefti=j;
                    break;
                }
            }
            //往右
            Integer righti=null;
            for (int j = i+1; j < as.length; j++) {
                if(as[j]>a){
                    righti=j;
                    break;
                }
            }
            //
            We e=new We();
            e.left=lefti;
            e.cur=i;
            e.right=righti;
            rls.add(e);
        }
        return rls;
    }
}
