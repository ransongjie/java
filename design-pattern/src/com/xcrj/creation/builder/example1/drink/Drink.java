package com.xcrj.creation.builder.example1.drink;

import com.xcrj.creation.builder.example1.Item;
import com.xcrj.creation.builder.example1.packing.Bottle;
import com.xcrj.creation.builder.example1.packing.Packing;

public abstract class Drink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

    @Override
    public abstract String name();
}
