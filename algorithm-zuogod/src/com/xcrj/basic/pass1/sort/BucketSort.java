package com.xcrj.basic.pass1.sort;

//统计每个年龄的员工数量
public class BucketSort {
    public static void main(String[] args) {
        int[] as={20,65};
        int[] cnts=new int[66];
        for(int i=0;i<as.length;i++){
            cnts[as[i]]++;
        }

        for (int i = 20; i < 66; i++) {
            if(cnts[i]!=0) System.out.println(i+": "+cnts[i]);
        }
    }
}
