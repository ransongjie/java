package com.xcrj.company.pass1;

public class A039ValidParentheses {
    public static boolean validParentheses(String s){
        char[]stack=new char[s.length()];
        int size=0;
        for (char c:
             s.toCharArray()) {
            if(c=='('||c=='['||c=='{'){
                stack[size++]=c=='('?')':(c=='['?']':'}');
            }else{
                //栈空
                if(size==0) return false;
                //括号不匹配
                if(c!=stack[--size]) return false;
            }
        }

        return size==0;//栈空
    }
}
