package com.xcrj.basic.pass2.str;

/**
 * Manacher 求回文串的最大长度
 * [..., i', maxC, i, ...]
 * 1. i在maxC+maxR内：扩展基础 r(i)=min{(maxC+maxR-i), maxC-(i-maxC)}
 * r=i关于maxC的对称点i'的回文半径r(i')
 * - r(i')在maxR内，r(i)=r(i')=rs[maxC-(i-maxC)], 外扩将失败
 * - r(i')在maxR外，r(i)=(maxC+maxR-i), 外扩将失败 (若可以外扩则r(i')不在maxR外)
 * - r(i')刚好压线，r(i)=(maxC+maxR-i)=rs[maxC-(i-maxC)], 外扩可能成功
 * 2. i不在maxC+maxR内(外或压线)：扩展基础 r(i)=1
 * 步骤
 * 1. 转换字符串
 * 2. 历史最大回文半径，历史最大回文半径的回文中心，每个字符的回文半径
 * 3. i的扩展基础，继续扩展i
 * 4. 更新历史最大回文半径，历史最大回文半径的回文中心
 * - i在maxC+maxR内，扩展基础 r(i)=min{(maxC+maxR-i), maxC-(i-maxC)}
 * - i不在maxC+maxR内，扩展基础 r(i)=1
 */
public class Manacher {
    public static void main(String[] args) {
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
                throw new RuntimeException();
            }
        }
    }

    public static int manacher(String str) {
        if(str==null||str.length()==0) return 0;
        String cstr=convert(str);
        char[]cs=cstr.toCharArray();
        
        int[] rs=new int[cs.length];
        int maxC=0;
        int maxR=0;
        for (int i = 0; i < rs.length; i++) {
            rs[i]=i>=(maxC+maxR)?1:Math.min((maxC+maxR-i),rs[maxC-(i-maxC)]);
            while(i-rs[i]>-1&&i+rs[i]<cs.length&&cs[i-rs[i]]==cs[i+rs[i]]){
                rs[i]=++rs[i];
            }

            if(rs[i]>maxR){
                maxR=rs[i];
                maxC=i;
            }
        }

        return maxR-1;
    }

    private static String convert(String str) {
        StringBuilder sb=new StringBuilder();
        sb.append('#');
        for(char c:str.toCharArray()){
            sb.append(c).append('#');
        }
        return sb.toString();
    }


    private static String convert2(String str) {
        char[] cs=str.toCharArray();
        char[] cs2=new char[2*cs.length+1];
        for (int i = 0,j=0; i < cs2.length; i++) {
            //奇数 偶数的区别在二进制位上 在最后1位
            cs2[i]=(i&1)==0?'#':cs[j++];
        }
        return String.valueOf(cs2);
    }

    public static int manacher2(String str) {
        if(str==null||str.length()==0) return 0;
        String cstr=convert2(str);
        char[]cs=cstr.toCharArray();
        
        int[]rs=new int[cs.length];
        int C=-1;
        int R=-1;
        int maxR=Integer.MIN_VALUE;
        
        for (int i = 0; i < cs.length; i++) {
            rs[i]=i>=R?1:Math.min(R-i,rs[C-(i-C)]);
            while(i-rs[i]>-1&&i+rs[i]<cs.length&&cs[i+rs[i]]==cs[i-rs[i]]){
                rs[i]++;
            }
            if(i+rs[i]>R){
                R=i+rs[i];
                C=i;
                maxR=Math.max(maxR, rs[i]);
            }
        }

        return maxR-1;
    }

    public static String getStr(int maxLen) {
        int len=(int)(Math.random()*(maxLen-1)+1);

        char[] cs=new char[len];
        for (int i = 0; i < len; i++) {
            //20~126的字符 ASCII
            int v=20+(int)(Math.random()*(126-20+1));
            cs[i]=(char)v;
        }

        return String.valueOf(cs);
    }
}
