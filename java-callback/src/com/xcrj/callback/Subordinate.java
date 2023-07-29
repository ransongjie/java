package com.xcrj.callback;

public class Subordinate {
    public void doSomething(CallbackInterface callbackInterface) {
        System.out.println("下属，尽力完成任务中。。。");
        try {
            Thread.currentThread().sleep(5*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("下属，任务完成通知领导。。。");
        callbackInterface.callback();
    }
}
