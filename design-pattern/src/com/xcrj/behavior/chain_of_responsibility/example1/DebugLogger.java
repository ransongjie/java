package com.xcrj.behavior.chain_of_responsibility.example1;

public class DebugLogger extends Logger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debug Logger: " + message);
    }

}
