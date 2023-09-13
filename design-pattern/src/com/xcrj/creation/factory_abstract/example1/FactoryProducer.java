package com.xcrj.creation.factory_abstract.example1;

import com.xcrj.creation.factory_abstract.example1.color.ColorFactory;
import com.xcrj.creation.factory_abstract.example1.shape.ShapeFactory;

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
