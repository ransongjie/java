package com.xcrj.behavior.observer.example1;

public abstract class Observer {
    // 目标
    protected Target target;

    public abstract void update();
}
