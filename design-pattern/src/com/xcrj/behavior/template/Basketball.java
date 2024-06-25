package com.xcrj.behavior.template;

public class Basketball extends Game {

    @Override
    public void prepare() {
        System.out.println("Prepare basketball");
    }

    @Override
    public void start() {
        System.out.println("Start basketball");
    }

    @Override
    public void stop() {
        System.out.println("Stop basketball");
    }

}
