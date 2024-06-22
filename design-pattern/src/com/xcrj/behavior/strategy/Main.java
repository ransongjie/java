package com.xcrj.behavior.strategy;

/*
策略模式实现加减乘除计算器
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.strategy.Main
 */
public class Main {
    public static void main(String[] args) {
        Operation operation1 = new Add();
        Operation operation2 = new Div();
        Calculator calculator = new Calculator(operation1);
        calculator.calculate(10, 2);
        calculator = new Calculator(operation2);
        calculator.calculate(10, 2);
    }
}
