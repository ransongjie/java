package com.xcrj.behavior.template;

public abstract class Game {

    public final void play() {
        prepare();
        start();
        stop();
    }

    public abstract void prepare();

    public abstract void start();

    public abstract void stop();
}