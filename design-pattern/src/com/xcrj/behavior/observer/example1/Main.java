package com.xcrj.behavior.observer.example1;

public class Main {
    public static void main(String[] args) {
        // 目标
        Target target = new Target();

        // 观察者
        new HexaObserver(target);
        new OctalObserver(target);
        new BinaryObserver(target);

        // 目标对象状态发生变化时，将通知所有观察者
        target.setState(20);
    }
}
