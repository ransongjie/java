package com.xcrj.basic.pass2.force;
/**
 * 字符串转换 种数
 * '0' 0种，字符串中含有0转换失败
 * '1' 2种，1单独构成，1和后面的数字2个构成
 * '2' 下一个字符在(0,7)之间 2种，否则1种
 * '3' 1种
 */
public class StrConvert {
   public static void main(String[] args) {
    String str="1111";
    System.out.println(strConvert(0, str.toCharArray()));
   } 

   public static int strConvert(int i,char[]cs) {
       if(i>=cs.length) return 1;
       if(cs[i]=='0') return 0;
       int r=0;
       if(cs[i]=='1') return r+=strConvert(i+1,cs)+strConvert(i+2,cs);
       if(cs[i]=='2'&&i+1<cs.length&&cs[i+1]<'7'&&cs[i+1]>'0') return r+=strConvert(i+1,cs)+strConvert(i+2,cs);
       return r+=strConvert(i+1, cs);
   }
}
