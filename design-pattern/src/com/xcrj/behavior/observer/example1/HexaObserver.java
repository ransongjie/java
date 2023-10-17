package com.xcrj.behavior.observer.example1;

public class HexaObserver extends Observer {

    public HexaObserver(Target target) {
        this.target = target;
        this.target.register(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(target.getState()).toUpperCase());
    }
}
