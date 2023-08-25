package com.xcrj.creation.builder.example1.drink;

public class Coke extends Drink {
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
