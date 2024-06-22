package com.xcrj.behavior.memento;

/*
Traget创建Memento记录Target的状态
Saver存储Memento
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.memento.Main
 */
public class Main {
    public static void main(String[] args) {
        Saver saver = new Saver();
        Target o = new Target(0);

        o.setState(1);
        Memento m1 = o.mem();
        saver.save(m1);
        o.setState(2);
        Memento m2 = o.mem();
        saver.save(m2);

        System.out.println("Target state: " + saver.get().getState());
        System.out.println("Target state: " + saver.get().getState());
    }
}