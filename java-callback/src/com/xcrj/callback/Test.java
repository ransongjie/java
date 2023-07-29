package com.xcrj.callback;

public class Test {
    public static void main(String[] args) {
        Subordinate subordinate=new Subordinate();
        Leader leader=new Leader(subordinate);
        leader.arrange();
    }
}
