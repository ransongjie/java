package com.xcrj.behavior.nulla;

public class NullApple extends Apple {

    @Override
    public String getName() {
        return "Null Apple";
    }

    @Override
    public boolean isNull() {
        return true;
    }

}
