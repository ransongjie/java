package com.xcrj.concurrent.lock.synd;
/**
 * 交替输出
 * synchronized(this) this.wait(); this.notifyAll();
 * t1输出a，t2输出b，t3输出c，共输出5次
 * abc abc abc abc abc
 */
public class Synchronized7 {
    public static void main(String[] args) {
        WaitNotify waitNotify=new WaitNotify(1, 5);
        Thread t1=new Thread(()->{
            waitNotify.hello(1, 2, "a");
        });
        Thread t2=new Thread(()->{
            waitNotify.hello(2, 3, "b");
        });
        Thread t3=new Thread(()->{
            waitNotify.hello(3, 1, "c");
        });

        t1.start();
        t2.start();
        t3.start();
    }

}

class WaitNotify {
    //控制执行顺序
    int id;
    int loopNum;
    WaitNotify (int id,int loopNum){
        this.id=id;
        this.loopNum=loopNum;
    }

    public void hello(int curId,int nxtIdx,String msg){
        for (int i = 0; i < loopNum; i++) {
            synchronized(this){
                //非当前可执行id则等待
                while(this.id!=curId){
                    try{
                        this.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                
                System.out.println(msg);
                this.id=nxtIdx;
                this.notifyAll();
            }
        }
    }

}
