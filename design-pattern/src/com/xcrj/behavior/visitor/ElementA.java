package com.xcrj.behavior.visitor;

public class ElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
