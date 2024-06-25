package com.xcrj.behavior.interpreter;

public class MyInterpreter implements Interpreter {
    String keyword;

    MyInterpreter(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(keyword);
    }

}
