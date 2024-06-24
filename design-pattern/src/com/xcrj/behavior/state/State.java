package com.xcrj.behavior.state;

public interface State {
    void doAction(StateContext context);

    void sayHello();
}