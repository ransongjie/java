package com.xcrj.behavior.observer.example1;

public class OctalObserver extends Observer {

    public OctalObserver(Target target) {
        this.target = target;
        this.target.register(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "
                + Integer.toOctalString(target.getState()));
    }
}
