package com.xcrj.creation.builder.example1;

import com.xcrj.creation.builder.example1.burger.ChickenBurger;
import com.xcrj.creation.builder.example1.burger.VegBurger;
import com.xcrj.creation.builder.example1.drink.Coke;
import com.xcrj.creation.builder.example1.drink.Pepsi;

/**
 * 构建套餐
 */
public class MealBuilder {
    /**
     * 套餐 Veg Meal
     */
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    /**
     * 套餐 Non-Veg Meal
     */
    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
