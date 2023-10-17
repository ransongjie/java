package com.xcrj.behavior.observer.example1;

public class BinaryObserver extends Observer {

    public BinaryObserver(Target target) {
        this.target = target;
        this.target.register(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(target.getState()));
    }
}