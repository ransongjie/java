package com.xcrj.concurrent.atom;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference
 */
public class AtomicReference0 {
    private AtomicReference<String> atomicString = new AtomicReference<>("xcrj0");

    public void setValue(String newValue) {
        atomicString.set(newValue);
    }

    public String getValue() {
        return atomicString.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReference0 atomicReference0 = new AtomicReference0();

        // 创建一个线程，间隔1S输出 reference 的值
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("当前值: " + atomicReference0.getValue());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        // 主线程每隔 2 秒更新 reference 的值
        for (int i = 0; i < 3; i++) {
            int rand = (int) (Math.random() * 100);
            Thread.sleep(2000);
            atomicReference0.setValue("xcrj" + rand);
        }
    }
}
