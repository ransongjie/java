package com.xcrj.company.pass1;

public class A052FindCelebrity {

    //系统提供该方法
    //i=j return false
    private static boolean know(int i,int j){
        return true;
    }

    public static int findCelebrity(int[]as){
        //！找到可能的明星
        int cand=0;
        for (int i = 0; i < as.length; i++) {
            //我认识i, 我肯定不是明星, i可能是明星
            //我不认识i，i肯定不是明星
            if(know(cand,i)){
                cand=i;
            }
        }

        //明星不认识所有人
        //！cand的右侧在上面验证了
        //验证cand的左侧
        for (int i = 0; i < cand; i++) {
            if(know(cand,i)){//明星认识某个人
                return -1;
            }
        }

        //所有人认识明星
        for (int i = 0; i < as.length; i++) {
            //?? i!=cand&&
            if(!know(i,cand)){//某个人不认识明星
                return -1;
            }
        }

        return cand;
    }
}
