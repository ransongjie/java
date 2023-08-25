package com.xcrj.creation.abstract_factory.example1;

import com.xcrj.creation.abstract_factory.example1.color.Color;
import com.xcrj.creation.abstract_factory.example1.shape.Shape;

public abstract class AbstractFactory {
    //获取颜色产品
    public abstract Color getColor(String color);

    //获取形状产品
    public abstract Shape getShape(String shape);
}
