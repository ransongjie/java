package com.xcrj.reflect;

public class Apple {
    public String name;
    private String value;

    public Apple() {
        name = "Apple";
        value = "5元";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }

    public String getValue() {
        return value;
    }
}
