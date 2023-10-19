package com.xcrj.sync;

public class ThreadTest1a {
    JManager j = new JManager();

    public static void main(String[] args) {
        new ThreadTest1a().call();
    }

    void call() {
        for (int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        public void run() {
                            //对比 ThreadTest1将 for (int i = 0; i < 100; i++) { 》while
                            while (true) {
                                j.accumulate();
                            }
                        }
                    }
            ).start();
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        j.subtract();
                    }
                }
            }).start();
        }
    }
}

class JManager {
    private int j = 0;

    public synchronized void subtract() {
        j--;
    }

    public synchronized void accumulate() {
        j++;
    }
}
