package com.xcrj.creation.factory.example1.shape;

import com.xcrj.creation.factory.example1.shape.Circle;
import com.xcrj.creation.factory.example1.shape.Rectangle;
import com.xcrj.creation.factory.example1.shape.Shape;
import com.xcrj.creation.factory.example1.shape.Square;

public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
