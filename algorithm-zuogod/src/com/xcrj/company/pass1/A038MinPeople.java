package com.xcrj.company.pass1;

public class A038MinPeople {
    public static int minPeople(int[]as){
        //((c+x)/(x+1))*(x+1)
        int ans=0;
        // 0人说还有x个人
        int x=as[0];
        // 有1个人说了这个事情
        int c=1;
        for (int i = 1; i < as.length; i++) {
            if(x!=as[i]){
                ans+=((c+x)/(x+1))*(x+1);
                x=as[i];
                c=1;
            }else{
                c++;
            }
        }

        //i<len就退出for了，还有剩余的
        return ans+=((c+x)/(x+1))*(x+1);
    }
}
