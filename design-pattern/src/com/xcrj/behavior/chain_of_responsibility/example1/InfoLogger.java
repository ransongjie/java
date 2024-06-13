package com.xcrj.behavior.chain_of_responsibility.example1;

public class InfoLogger extends Logger {
    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Info Logger: " + message);
    }

}
