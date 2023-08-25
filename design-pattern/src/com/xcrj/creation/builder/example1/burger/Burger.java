package com.xcrj.creation.builder.example1.burger;

import com.xcrj.creation.builder.example1.Item;
import com.xcrj.creation.builder.example1.packing.Packing;
import com.xcrj.creation.builder.example1.packing.Wrapper;

public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

    @Override
    public abstract String name();
}
