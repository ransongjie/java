package com.xcrj.behavior.state;

/*
 * 上下文持有的状态引用，能够被状态对象改变
 * javac -d ./cp -encoding UTF-8
 * java -cp ./cp -XX:+ShowCodeDetailsInExceptionMessages com.xcrj.behavior.state.Main
 */
public class Main {
    public static void main(String[] args) {
        StateContext context = new StateContext();

        State startState = new StartState();
        startState.doAction(context);
        context.getState().sayHello();

        State stopState = new StopState();
        stopState.doAction(context);
        context.getState().sayHello();
    }
}
