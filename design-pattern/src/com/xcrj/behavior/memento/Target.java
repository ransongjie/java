package com.xcrj.behavior.memento;

public class Target {
    private int state;

    public Target(int state) {
        this.state = state;
    }

    // 创建备忘录
    public Memento mem() {
        return new Memento(this.state);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
