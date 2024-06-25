package com.xcrj.behavior.interpreter;

/*
 * 解释器解释文法
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.interpreter.Main
 */
public class Main {
    public static void main(String[] args) {
        MyInterpreter myInterpreter1 = new MyInterpreter("xcrj");
        System.out.println("xcrj 123 contains xcrj? " + myInterpreter1.interpret("xcrj 123"));

        MyInterpreter myInterpreter2 = new MyInterpreter("apple");

        AndInterpreter andInterpreter = new AndInterpreter(myInterpreter1, myInterpreter2);
        System.out.println("xcrj 123 appl contains xcrj and apple? " + andInterpreter.interpret("xcrj 123 appl"));
    }
}
