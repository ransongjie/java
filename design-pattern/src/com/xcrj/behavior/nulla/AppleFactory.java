package com.xcrj.behavior.nulla;

public class AppleFactory {
    public static Apple createApple(String name) {
        if (name.contains("fuji")) {
            return new FujiApple(name);
        }

        return new NullApple();
    }
}
