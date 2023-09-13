package com.xcrj.creation.factory_abstract.example1.color;

import com.xcrj.creation.factory_abstract.example1.AbstractFactory;
import com.xcrj.creation.factory_abstract.example1.shape.Shape;

public class ColorFactory extends AbstractFactory {

    //使用 getColor 方法获取颜色类型的对象
    @Override
    public Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }
        if (colorType.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (colorType.equalsIgnoreCase("BLUE")) {
            return new Blue();
        } else if (colorType.equalsIgnoreCase("GREEN")) {
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
