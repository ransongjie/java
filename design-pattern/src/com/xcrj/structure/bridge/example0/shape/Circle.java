package com.xcrj.structure.bridge.example0.shape;

import com.xcrj.structure.bridge.example0.color.DrawAPI;

public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        super.drawAPI.drawCircle(radius, x, y);
    }
}
