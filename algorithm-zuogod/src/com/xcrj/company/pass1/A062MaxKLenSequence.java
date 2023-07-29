package com.xcrj.company.pass1;

public class A062MaxKLenSequence {

    public static String maxKLenSeq(String s,int k){
        if(s==null||k>s.length()){
            return "";
        }
        int n=s.length();
        char[]cs=s.toCharArray();
        char[]stack=new char[n];
        int top=0;
        for (int i = 0; i < n; i++) {
            //栈中有元素，栈中元素+剩余元素>k，栈顶元素字典序<cur
            while(top>0&&top+n-i>k&&stack[top-1]<cs[i]){
                //出栈栈顶
                top--;
            }
            //第二种情况，栈中元素+剩余元素=k
            if(top+n-i==k){
                return String.valueOf(stack,0,top)+String.valueOf(cs,i,n);
            }
            stack[top++]=cs[i];
        }
        //第一种情况，走到字符串尾部
        return String.valueOf(stack,0,k);
    }
}
