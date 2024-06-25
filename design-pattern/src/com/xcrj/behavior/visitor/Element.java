package com.xcrj.behavior.visitor;

public interface Element {
    void accept(Visitor visitor);
}
