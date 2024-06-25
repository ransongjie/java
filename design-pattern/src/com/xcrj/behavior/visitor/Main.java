package com.xcrj.behavior.visitor;

/*
 * 访问者访问元素
 * 元素接受访问者的访问
 * 元素集合存储元素，并接受访问者的访问
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.visitor.Main
 */
public class Main {
    public static void main(String[] args) {
        Visitor1 visitor1 = new Visitor1();
        ElementSet es = new ElementSet();
        es.accept(visitor1);
    }
}
