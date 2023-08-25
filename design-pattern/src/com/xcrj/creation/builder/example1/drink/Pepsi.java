package com.xcrj.creation.builder.example1.drink;

public class Pepsi extends Drink {
    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
