package com.xcrj.company.pass1;

import java.util.LinkedList;

/**
 * 带括号的四则运算表达式结果
 */
public class A014Bracket {
    public static int bracket(String s){
        return f(s.toCharArray(),0)[0];
    }

    //int[0] ans int[1] endIdx
    private static int[] f(char[] cs,int i){
        LinkedList<String> que=new LinkedList<>();
        int cur=0;
        int[]rs;
        while(i<cs.length&&cs[i]!=')'){
            if(cs[i]>='0'&&cs[i]<='9'){
                cur=cur*10+cs[i++]-'0';
//                que.offerLast(String.valueOf(cur));// add(que,cur);
            }else if(cs[i]!='('){//运算符
                add(que,cur);
                que.offerLast(String.valueOf(cs[i++]));
                cur=0;
            }else{
                rs=f(cs,i+1);//跳过左括号
                cur=rs[0];
//                que.offerLast(String.valueOf(cur));// add(que,cur);
                i=rs[1]+1;
            }
        }
        add(que,cur);//
        return new int[]{get(que),i};
    }

    //栈顶字符是乘除则先取出再运算
    private static void add(LinkedList<String> que,int cur){
        if(!que.isEmpty()){
            String opr=que.pollLast();
            if(opr.equals("+")||opr.equals("-")){
                que.offerLast(opr);
            }else{
                Integer num=Integer.valueOf(que.pollLast());
                cur=opr.equals("*")?num*cur:num/cur;
            }
        }

        que.offerLast(String.valueOf(cur));
    }

//    private static int get(LinkedList<String> que){
//        int cur=0;
//        int nxt;
//        String opr;
//        while(!que.isEmpty()){
//            cur=Integer.valueOf(que.pollFirst());
//            opr=que.pollFirst();
//            nxt=Integer.valueOf(que.pollFirst());
//            cur=cur+(opr.equals("+")?nxt:-nxt);
//        }
//
//        return cur;
//    }

    //队列中只有加减
    private static int get(LinkedList<String> que){
        int res=0;
        boolean isAdd=true;//默认是+，队列中只有1个数字则一定是正数
        int num;
        String cur;//+，-，数字
        while (!que.isEmpty()){
            cur=que.pollFirst();
            if(cur.equals("+")){
                isAdd=true;
            }else if (cur.equals("-")){
                isAdd=false;
            }else{
                num=Integer.valueOf(cur);
                res+=(isAdd?num:-num);
            }
        }

        return res;
    }
}
