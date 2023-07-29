package com.xcrj;

import com.xcrj03.Apple;

import java.util.ServiceLoader;

public class Test1 {
    public static void main(String[] args) {
        ServiceLoader<Apple> load = ServiceLoader.load(Apple.class);
        for (Apple f : load) {
            f.fruits();
        }
    }
}