package com.xcrj.creation.factory.example1;

import com.xcrj.creation.factory.example1.shape.Shape;
import com.xcrj.creation.factory.example1.shape.ShapeFactory;

/**
 * 形状工厂，圆形，正方形，矩形
 */
public class Main {
    public static void main(String[] args) {
        //创建工厂
        ShapeFactory shapeFactory = new ShapeFactory();

        //生产CIRCLE产品
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shape1.draw();

        //生产RECTANGLE产品
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //生产SQUARE产品
        Shape shape3 = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shape3.draw();
    }
}
