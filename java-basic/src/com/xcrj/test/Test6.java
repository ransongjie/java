package com.xcrj.test;

public class Test6 {
    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("xcrj");
        }
    }
}
