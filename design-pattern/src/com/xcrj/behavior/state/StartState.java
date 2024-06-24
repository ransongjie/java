package com.xcrj.behavior.state;

public class StartState implements State {

    @Override
    public void doAction(StateContext context) {
        System.out.println("Start State");
        context.setState(this);
    }

    @Override
    public void sayHello() {
        System.out.println("StartState hello");
    }

}
