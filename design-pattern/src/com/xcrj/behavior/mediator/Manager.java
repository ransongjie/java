package com.xcrj.behavior.mediator;

// 经理
public class Manager implements Colleague {
    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void chat(String msg) {
        mediator.transfer(this, msg);
    }

    @Override
    public void info(String msg) {
        System.out.println("Employee send to Manager a msg: " + msg);
    }
}
