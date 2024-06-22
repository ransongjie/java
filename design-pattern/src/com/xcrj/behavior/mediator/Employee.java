package com.xcrj.behavior.mediator;

// 员工
public class Employee implements Colleague {
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
        System.out.println("Manager send to Employee a msg: " + msg);
    }

}
