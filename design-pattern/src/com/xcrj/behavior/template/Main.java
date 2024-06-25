package com.xcrj.behavior.template;

/*
 * 模板定义步骤，子类实现步骤
javac -d ./cp -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.template.Main
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Football();
        game.play();

        Game game2 = new Basketball();
        game2.play();
    }
}
