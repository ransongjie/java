package com.xcrj.callback;

public class Leader implements CallbackInterface{
    private Subordinate subordinate;

    public Leader(Subordinate subordinate){
        this.subordinate=subordinate;
    }

    public void arrange() {
        System.out.println("安排任务给下属");
        subordinate.doSomething(this);
    }

    @Override
    public void callback() {
        System.out.println("你好，领导！任务已完成");
    }
    
}
