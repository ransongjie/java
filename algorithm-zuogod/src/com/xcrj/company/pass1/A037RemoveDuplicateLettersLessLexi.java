package com.xcrj.company.pass1;

public class A037RemoveDuplicateLettersLessLexi {
    /**
     * 单调栈，存储递增的字符，相同字符只保留1个
     * 栈顶字符>当前字符 && 后面还有当前字符：出栈
     */
    public static String removeDuplicateLettersLessLexi(String s){
        //计数器
        int[]cnts=new int[26];
        for (char c :
                s.toCharArray()) {
            cnts[c-'a']++;
        }
        //单调栈
        char[]stack=new char[26];
        int size=0;//单调栈大小
        boolean[]isEntered=new boolean[26];
        for (char c :
                s.toCharArray()) {
            if (!isEntered[c - 'a']){
                isEntered[c - 'a']=true;
                //何时出栈：单调栈不空&&单调栈顶字符>当前字符&&单调栈顶字符在后续字符中还有
                while(size>0&&stack[size-1]>c&&cnts[stack[size-1]-'a']>0){
                    isEntered[stack[size-1]-'a']=false;
                    size--;
                }
                stack[size++]=c;
            }
            //当前字符处理过
            cnts[c-'a']--;
        }

        return String.valueOf(stack,0,size);
    }
}
