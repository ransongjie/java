package com.xcrj.creation.prototype.example0;

/**
 * 以拷贝的方式快速创建火神分身
 * <p>
 * 快速创建程序中需要大量使用的相似对象
 */
public class Main {
    public static void main(String[] args) {
        // new 对象
        FireGod fireGod = new FireGod();
        // clone 对象
        // 火神分身1
        FireGod fireGod1 = fireGod.clone();
        // 火神分身2
        FireGod fireGod2 = fireGod.clone();
        // 火神分身3
        FireGod fireGod3 = fireGod.clone();
    }
}
