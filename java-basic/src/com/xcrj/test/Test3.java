package com.xcrj.test;

public class Test3 {

}

interface Playable {
    void play();
}
interface Bounceable {
    void play();
}
interface Rollable extends Playable, Bounceable {
    //public static final Ball ball = new Ball("PingPang");
    Ball ball = new Ball("PingPang");
}
class Ball implements Rollable {
    private String name;
    public String getName() {
        return name;
    }
    public Ball(String name) {
        this.name = name;
    }
    public void play() {
        //编译报错，不能对final变量赋值
//        ball = new Ball("Football");
//        System.out.println(ball.getName());
    }
}
