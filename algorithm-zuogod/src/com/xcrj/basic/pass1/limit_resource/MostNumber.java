package com.xcrj.basic.pass1.limit_resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


//出现次数最多数字 大文件统计 限制内存大小 todo
public class MostNumber {
    public static void main(String[] args) throws IOException {
        //结果5.48GB文件
        // genBigFile(4,"xcrj.txt",10000);

    }

    // 生成xGB的大文件，int随机数. 
    private static void genBigFile(int xGB, String fileName, int maxV) throws IOException {
        File dest = new File("src/com/xcrj/hash/" + fileName);
        Writer writer = new FileWriter(dest,true);
        //
        int up = 1000;
        int typeLen=4;
        int times = xGB *1000 * 1000 / (up * typeLen) * 1000;
        for (int k = 0; k < times; k++) {
            StringBuilder sb = new StringBuilder(up * 2);
            int v;
            for (int i = 0; i < up; i++) {
                v = (int) (Math.random() * maxV);
                sb.append(v).append("\r\n");
                if (i == up - 1)
                    sb.deleteCharAt(sb.length() - 1);
            }
            writer.write(sb.toString());
            writer.flush();
        }
        //
        if(writer!=null){
            writer.close();
        }
    }

    //todo
}
