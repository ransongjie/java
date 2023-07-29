package com.xcrj.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIOServer BIO服务端
 * 
 * BIO：建立1个连接创建1个线程，该线程负责建立连接，读写和业务处理
 */
public class BIOServer{
    public static void main(String[] args) throws Exception{
        /* 
         * 核心线程数=0，最大线程数=Integer.MAX_VALUE，新任务来了将直接创建新线程执行这个新任务，
         * 使用SynchronousQueue队列中转新任务，新线程创建完成后将马上执行新任务，不会在任务队列中存储很久，
         * 因此使用同步队列中转一会儿新任务即可
         */
        ExecutorService es=Executors.newCachedThreadPool();

        //1. 建立ServerSocket
        ServerSocket serverSocket=new ServerSocket(7000);
        System.out.println("服务器启动....");

        while(true){
            System.out.println("线程信息 id="+Thread.currentThread().getId()+" 名字="+Thread.currentThread().getName());
            System.out.println("等待连接....");
            //2. 监听，阻塞当前线程，直到获取到连接
            final Socket socket=serverSocket.accept();//accept
            System.out.println("连接到一个客户端");
            
            // 建立1个连接创建1个线程
            es.execute(()->{handler(socket);});//handler

        }
    }

    //编写一个handler方法，和客户端通讯
    public static void handler(Socket socket) {
        try{
            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //3. 通过socket 获取输入流
            InputStream is=socket.getInputStream();  
            
            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
                System.out.println("read....");
                //阻塞式方法
                int read=is.read(bytes);//read
                if(read!=-1){
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));//business
                }else{
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("关闭和client的连接");
            try {
                //4. 关闭socket连接
                socket.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}