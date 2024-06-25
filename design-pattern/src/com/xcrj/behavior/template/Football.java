package com.xcrj.behavior.template;

public class Football extends Game {

    @Override
    public void prepare() {
        System.out.println("Prepare football");
    }

    @Override
    public void start() {
        System.out.println("Start football");
    }

    @Override
    public void stop() {
        System.out.println("Stop football");
    }

}
