package com.xcrj.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

public class ElementSet implements Element{
    public final List<Element> es;

    ElementSet(){
        es=new ArrayList<>();
        es.add(new ElementA());
        es.add(new ElementB());
        es.add(new ElementC());
    }

    @Override
    public void accept(Visitor visitor) {
        System.out.println("Visit ElementSet");
        for (Element element : es) {
            element.accept(visitor);
        }
    }
    
}
