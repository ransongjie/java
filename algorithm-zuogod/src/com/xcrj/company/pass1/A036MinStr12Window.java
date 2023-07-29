package com.xcrj.company.pass1;

public class A036MinStr12Window {
    public static int minStr12Window(String s1,String s2){
        if(s1==null||s2==null||s1.length()<s2.length()){
            return Integer.MAX_VALUE;
        }
        //构建欠债表 ascii码表
        int all=0;
        all=s2.length();
        int[] asciis=new int[256];
        for (char c :
                s2.toCharArray()) {
            asciis[c]++;
        }

        //r先移动，满足要求后，l再移动
        int ans=Integer.MAX_VALUE;
        int l=0,r=0;
        while(r<s1.length()){
            //先把钱还完，可能多还
            asciis[s1.charAt(r)]--;
            if(asciis[s1.charAt(r)]>=0){
                all--;
            }
            //再把多还的拿回来
            if(all==0){
                //从l~r, 逐个字符看是不是还多了
                while(asciis[s1.charAt(l)]<0){
                    asciis[s1.charAt(l++)]++;
                }
                ans=Math.min(ans,r-l+1);
                //多拿回了 s1[l], 又开始欠钱了
                asciis[s1.charAt(l++)]++;
                all++;
            }
            r++;
        }

        return ans==Integer.MAX_VALUE?0:ans;
    }


}
