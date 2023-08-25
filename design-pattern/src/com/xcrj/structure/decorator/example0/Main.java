package com.xcrj.structure.decorator.example0;

import com.xcrj.structure.decorator.example0.decorator.RedShapeDecorator;
import com.xcrj.structure.decorator.example0.decorator.ShapeDecorator;
import com.xcrj.structure.decorator.example0.shape.Circle;
import com.xcrj.structure.decorator.example0.shape.Rectangle;
import com.xcrj.structure.decorator.example0.shape.Shape;

/**
 * 形状，变色装饰器
 */
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
