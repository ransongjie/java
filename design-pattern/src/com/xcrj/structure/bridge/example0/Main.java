package com.xcrj.structure.bridge.example0;

import com.xcrj.structure.bridge.example0.color.GreenCircle;
import com.xcrj.structure.bridge.example0.color.RedCircle;
import com.xcrj.structure.bridge.example0.shape.Circle;
import com.xcrj.structure.bridge.example0.shape.Shape;

/**
 * 圆形，红色圆形，绿色圆形
 */
public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
