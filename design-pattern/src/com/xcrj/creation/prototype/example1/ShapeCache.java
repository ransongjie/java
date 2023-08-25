package com.xcrj.creation.prototype.example1;

import com.xcrj.creation.prototype.example1.shape.Circle;
import com.xcrj.creation.prototype.example1.shape.Rectangle;
import com.xcrj.creation.prototype.example1.shape.Shape;
import com.xcrj.creation.prototype.example1.shape.Square;

import java.util.Hashtable;

public class ShapeCache {
    // Hashtable 用来模拟数据库
    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    /**
     * 对每种形状都运行数据库查询，并创建该形状
     * shapeMap.put(shapeKey, shape);
     * 例如，我们要添加三种形状
     */
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
