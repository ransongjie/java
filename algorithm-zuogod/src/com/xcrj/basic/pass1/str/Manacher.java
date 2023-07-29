package com.xcrj.basic.pass1.str;
//最长回文串
public class Manacher {
    public static void main(String[] args) {
        // String str="abcddcba";
        // System.out.println(manacher(str));

        int times=100000;
        int maxLen=1000;
        for (int i = 0; i < times; i++) {
            String str=getStr(maxLen);
            int len1=manacher(str);
            int len2=manacher2(str);
            if(len1!=len2){
                System.out.println("not good");
                System.out.println(str);
                System.out.println(len1);
                System.out.println(len2);
            }
        }
    }

    public static int manacher(String str) {
        if(str==null||str.length()==0) return 0;

        String cstr=convert(str);
        char[]cs=cstr.toCharArray();
        //每个字符的回文半径
        int[] rs=new int[cstr.length()];
        int maxR=0;//历史最大回文半径
        int maxC=0;//历史最大回文半径的回文中心
        for (int i = 0; i < cstr.length(); i++) {
            //1
            rs[i]=i>=(maxC+maxR)?1:Math.min((maxC+maxR-i),rs[maxC-(i-maxC)]);
            while(i+rs[i]<cs.length&&i-rs[i]>-1&&cs[i-rs[i]]==cs[i+rs[i]]){
                ++rs[i];
            }
            
            if(rs[i]>maxR){
                maxR=rs[i];
                maxC=i;
            }
        }
        return maxR-1;//回文直径中有#，回文直径是奇数多1个#
    }

    public static String convert(String str) {
        StringBuilder sb=new StringBuilder();
        sb.append("#");
        char[]cs=str.toCharArray();
        for (char c : cs) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }

    public static String convert2(String str) {
        char[]cs=str.toCharArray();
        char[]cs2=new char[cs.length*2+1];
        //
        // for (int i = 0,j=0; i < cs2.length; i++,j++) {
        for (int i = 0,j=0; i < cs2.length; i++) {
            cs2[i]=(i&1)==0?'#':cs[j++];
        }
        //
        return String.valueOf(cs2);
    }

    public static int manacher2(String str) {
        if(str==null||str.length()==0) return 0;

        String cstr=convert2(str);
        char[]cs=cstr.toCharArray();
        //每个字符的回文半径
        int[] rs=new int[cs.length];
        //
        int R=-1;//Right
        int C=-1;
        int maxr=Integer.MIN_VALUE;
        for (int i = 0; i < cs.length; i++) {//内部求i的回文半径
            rs[i]=i>=R?1:Math.min(R-i,rs[C-(i-C)]);
            while(i+rs[i]<cs.length&&i-rs[i]>-1){
                if(cs[i+rs[i]]==cs[i-rs[i]]){
                    rs[i]++;
                }else break;
            }

            if(i+rs[i]>R){
                R=i+rs[i];
                C=i;
                maxr=Math.max(maxr, rs[i]);
            }
        }

        return maxr-1;
    }

    public static String getStr(int maxLen) {
        int len=(int)(Math.random()*maxLen);
        while(len==0){
            len=(int)(Math.random()*maxLen);
        }
        //
        char[] cs=new char[len];
        for (int i = 0; i < len; i++) {
            //20~126的字符 ASCII
            int v=20+(int)(Math.random()*(126-20+1));
            char c=(char)v;
            cs[i]=c;
        }

        return String.valueOf(cs);
    }
}
