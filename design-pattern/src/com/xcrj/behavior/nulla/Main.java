package com.xcrj.behavior.nulla;

/*
 * 提供空对象替代不安全的null，防止NPE
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.nulla.Main
 */
public class Main {
    public static void main(String[] args) {
        Apple a1 = AppleFactory.createApple("fuji1");
        Apple a2 = AppleFactory.createApple("fuji2");
        Apple a3 = AppleFactory.createApple("yantai");
        System.out.println(a1.getName());
        System.out.println(a2.getName());
        System.out.println(a3.getName());
    }
}
