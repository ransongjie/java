package com.xcrj.nio.zerocopy;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class OldIOClient {
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",7001);
        String filename="result/protoc-3.6.1-win32.zip";
        InputStream is = new FileInputStream(filename);
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());

        byte[]buffer=new byte[4096];
        long read;
        long total=0;
        long startTime=System.currentTimeMillis();
        while((read=is.read(buffer))>=0){
            dos.write(buffer);
            total+=read;
        }

        long endTime=System.currentTimeMillis();
        System.out.println("发送总字节数： " + total + ", 耗时： " + (endTime - startTime));

        dos.close();
        is.close();
        socket.close();
    }
}
