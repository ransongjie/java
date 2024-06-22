package com.xcrj.behavior.iterator;

/*
接口：迭代器、容器
实现类：颜色迭代器、颜色容器
具体迭代器是具体容器的内部类
javac -d ./cp  -encoding UTF-8 *.java
java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.strategy.Main
 */
public class Main {
    public static void main(String[] args) {
        ColorContainer colorContainer = new ColorContainer();
        Iterator iterator = colorContainer.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
