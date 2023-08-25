package com.xcrj.creation.abstract_factory.example1;

import com.xcrj.creation.abstract_factory.example1.color.Color;
import com.xcrj.creation.abstract_factory.example1.shape.Shape;

public class Main {
    public static void main(String[] args) {
        //////////////////创建工厂
        //创建形状工厂
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
//////////////////创建产品
        //生产CIRCLE产品
        Shape shapeCircle = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shapeCircle.draw();

        //生产RECTANGLE产品
        Shape shapeRectangle = shapeFactory.getShape("RECTANGLE");
        //调用 Rectangle 的 draw 方法
        shapeRectangle.draw();

        //生产SQUARE产品
        Shape shapeSquare = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shapeSquare.draw();

//////////////////创建工厂
        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
//////////////////创建产品
        //生产RED产品
        Color colorRed = colorFactory.getColor("RED");
        //调用 Red 的 fill 方法
        colorRed.fill();

        //生产GREEN产品
        Color colorGreen = colorFactory.getColor("GREEN");
        //调用 Green 的 fill 方法
        colorGreen.fill();

        //生产BLUE产品
        Color colorBlue = colorFactory.getColor("BLUE");
        //调用 Blue 的 fill 方法
        colorBlue.fill();
    }
}
