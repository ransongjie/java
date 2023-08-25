package com.xcrj.structure.bridge.example0.shape;

import com.xcrj.structure.bridge.example0.color.DrawAPI;

public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
