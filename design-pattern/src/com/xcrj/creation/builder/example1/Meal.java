package com.xcrj.creation.builder.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * 一顿饭
 */
public class Meal {
    private List<Item> items = new ArrayList<Item>();

    /**
     * 点餐
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * 展示点餐信息
     */
    public void showItems() {
        for (Item item : items) {
            System.out.print("Item : " + item.name());
            System.out.print(", Packing : " + item.packing().pack());
            System.out.println(", Price : " + item.price());
        }
    }

    /**
     * 消费总额
     */
    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }


}
