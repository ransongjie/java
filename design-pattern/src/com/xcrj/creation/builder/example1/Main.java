package com.xcrj.creation.builder.example1;

/**
 * 建造者，一顿饭，菜品，套餐1，套餐2，汉堡，饮料，打包
 */
public class Main {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("套餐 Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        System.out.println("#########################");

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("套餐 Non-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }
}
