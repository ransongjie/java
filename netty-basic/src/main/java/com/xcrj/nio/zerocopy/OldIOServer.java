package com.xcrj.nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(7001);
        while(true){
            Socket socket=serverSocket.accept();//
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            try{
                byte[] byteArray=new byte[4096];
                while (true){
                    int read=dis.read(byteArray,0,byteArray.length);
                    if(-1==read) break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
