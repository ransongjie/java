package com.xcrj.company.pass1;

import java.util.ArrayList;
import java.util.List;

/**
 * 无序括号对变有效的所有方案
 */
public class A016RemoveInvalidParenthesis {

    /**
     *
     * @param s 括号对字符串
     * @return 所有方案
     */
    public static List<String> removeInvalidParentthesis(String s){
        List<String> ans=new ArrayList<>();
        process(s,ans,0,0,new char[]{'(',')'});
        return ans;
    }

    private static void process(String s,List<String> ans
            ,int chkIdx, int delIdx,char[] par){
        //cnt<0  右括号更多
        int cnt=0;
        for (int i = chkIdx; i < s.length(); i++) {
            if(s.charAt(i)==par[0]) cnt++;
            if(s.charAt(i)==par[1]) cnt--;
            if(cnt<0){
                for (int j = delIdx; j <= i; j++) {//=i
                    if(s.charAt(j)==par[1]&&(j==delIdx||s.charAt(j-1)!=s.charAt(j))){
                        process(s.substring(0,j)+s.substring(j+1),ans,i,j,par);//删除j，j之前已匹配
                    }
//                    ans.add(s);
                }
                return;//已全部匹配
            }
        }
        //cnt>0 左括号更多
        String reversed=new StringBuilder(s).reverse().toString();
        if(par[0]=='('){
            process(reversed,ans,chkIdx,delIdx,new char[]{')','('});
        }else{//处理完一次左括号，处理完一次右括号，就会走到这里
            ans.add(reversed);
        }
    }
}
