package com.xcrj.behavior.nulla;

public class FujiApple extends Apple {
    FujiApple(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNull() {
        return false;
    }

}
