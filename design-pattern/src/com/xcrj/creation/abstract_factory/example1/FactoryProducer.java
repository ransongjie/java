package com.xcrj.creation.abstract_factory.example1;

import com.xcrj.creation.abstract_factory.example1.color.ColorFactory;
import com.xcrj.creation.abstract_factory.example1.shape.ShapeFactory;

public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
