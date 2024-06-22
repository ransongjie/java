package com.xcrj.behavior.strategy;

public class Calculator {
    private Operation operation;

    public Calculator(Operation operation) {
        this.operation = operation;
    }

    public void calculate(int num1, int num2) {
        this.operation.execute(num1, num2);
    }
}
