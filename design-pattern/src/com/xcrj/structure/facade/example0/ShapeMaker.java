package com.xcrj.structure.facade.example0;

import com.xcrj.structure.facade.example0.shape.Circle;
import com.xcrj.structure.facade.example0.shape.Rectangle;
import com.xcrj.structure.facade.example0.shape.Shape;
import com.xcrj.structure.facade.example0.shape.Square;

public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}