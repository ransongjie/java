package com.xcrj.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Example1 {
    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }

    /**
     * RFC4648 Encoder and Decoder 
     * @throws UnsupportedEncodingException
     */
    public static void test1() {
        try{
            // String 》base64 String
            String str="我是xcrj";
            String encodedBase64=Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println(encodedBase64);
            // base64 String 》String
            byte[] decodedByte=Base64.getDecoder().decode(encodedBase64);
            String decodeStr=new String(decodedByte,"utf-8");
            System.out.println(decodeStr);
        }catch(UnsupportedEncodingException e){
            
        }
    }

    /**
     * RFC4648_URLSAFE  UrlEncoder and UrlDecoder 
     */
    public static void test2(){
        try{
            // String 》base64 String
            String str="我是xcrj";
            String encodedBase64=Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println(encodedBase64);
            // base64 String 》String
            byte[] decodedByte=Base64.getUrlDecoder().decode(encodedBase64);
            String decodeStr=new String(decodedByte,"utf-8");
            System.out.println(decodeStr);
        }catch(UnsupportedEncodingException e){
            
        }
    }

    /**
     * RFC2045 MimeEncoder and MimeDecoder 
     * Base64.Encoder getMimeEncoder()
     */
    public static void test3(){
        try{
            // String 》base64 String
            String str="我是xcrj";
            String encodedBase64=Base64.getMimeEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println(encodedBase64);
            // base64 String 》String
            byte[] decodedByte=Base64.getMimeDecoder().decode(encodedBase64);
            String decodeStr=new String(decodedByte,"utf-8");
            System.out.println(decodeStr);
        }catch(UnsupportedEncodingException e){
            
        }
    }

    /**
     * RFC2045 MimeEncoder and MimeDecoder 
     * Base64.Encoder getMimeEncoder(int lineLength, byte[] lineSeparator)
     * lineLength is set to 5, which rounded down to nearest multiple of 4
     * lineSeparator is set to #
     */
    public static void test4(){
        try{
            // String 》base64 String
            String str="我是我是xcrjxcrjxc";
            String encodedBase64=Base64.getMimeEncoder(5,"#".getBytes("utf-8")).encodeToString(str.getBytes("utf-8"));
            // 5oiR#5piv#5oiR#5piv#eGNy#anhj#cmp4#Yw==
            System.out.println(encodedBase64);
            // base64 String 》String
            byte[] decodedByte=Base64.getMimeDecoder().decode(encodedBase64);
            String decodeStr=new String(decodedByte,"utf-8");
            System.out.println(decodeStr);
        }catch(UnsupportedEncodingException e){
            
        }
    }
}
