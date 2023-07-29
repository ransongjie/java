package com.xcrj.company.pass1;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    MessageDigest hash;
    public Hash(String algorithm){
        try {
            hash=MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //hash算法使用简短的信息 信息摘要
    public String hashCode(String str){
        return DatatypeConverter
                .printHexBinary(hash.digest(str.getBytes()))
                .toUpperCase();
    }
}
