package com.xcrj.creation.prototype.example1;

import com.xcrj.creation.prototype.example1.shape.Shape;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        //将对象加载到数据库中
        ShapeCache.loadCache();

        //从数据库中获取对象
        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
