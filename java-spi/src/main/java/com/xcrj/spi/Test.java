package com.xcrj.spi;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<StandardCharger> serviceLoader=ServiceLoader.load(StandardCharger.class);
        serviceLoader.forEach(charger->{
            String name=charger.name();
            System.out.println(name);
        });
    }
}
