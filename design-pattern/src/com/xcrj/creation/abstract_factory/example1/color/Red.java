package com.xcrj.creation.abstract_factory.example1.color;

public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
