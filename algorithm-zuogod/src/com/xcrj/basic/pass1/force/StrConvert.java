package com.xcrj.basic.pass1.force;

//字符串转换 种数
public class StrConvert {
    public static void main(String[] args) {
        String str="1111";
        System.out.println(convert(0, str.toCharArray()));
    }

    public static int convert(int i,char[] cs) {
        if(i>=cs.length){
            return 1;
        }
        int r=0;
        if(cs[i]=='0') return 0;//0不对应任何字符
        if(cs[i]=='1') {
            r+=convert(i+1, cs)+convert(i+2, cs);
            return r;
        }
        if(cs[i]=='2'&&i+1<cs.length&&cs[i+1]>'0'&&cs[i+1]<'7') {
            r+=convert(i+1, cs)+convert(i+2, cs);
            return r;
        }
        r+=convert(i+1, cs);//剩余字符
        return r;
    }
}
