package com.xcrj.behavior.visitor;

public class Visitor1 implements Visitor{

    @Override
    public void visit(ElementA ea) {
        System.out.println("Visit ElementA");
    }

    @Override
    public void visit(ElementB eb) {
        System.out.println("Visit ElementB");
    }

    @Override
    public void visit(ElementC ec) {
        System.out.println("Visit ElementC");
    }
    
}
