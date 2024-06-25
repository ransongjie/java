package com.xcrj.behavior.interpreter;

public class OrInterpreter implements Interpreter {
    private Interpreter interpreter1;
    private Interpreter interpreter2;

    OrInterpreter(Interpreter interpreter1, Interpreter interpreter2) {
        this.interpreter1 = interpreter1;
        this.interpreter2 = interpreter2;
    }

    @Override
    public boolean interpret(String context) {
        return interpreter1.interpret(context) || interpreter2.interpret(context);
    }

}
