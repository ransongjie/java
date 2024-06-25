package com.xcrj.behavior.visitor;

public class ElementB implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
