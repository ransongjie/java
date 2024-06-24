package com.xcrj.behavior.state;

public class StopState implements State {

    @Override
    public void doAction(StateContext context) {
        System.out.println("Stop State");
        context.setState(this);
    }

    @Override
    public void sayHello() {
        System.out.println("StopState hello");
    }

}
