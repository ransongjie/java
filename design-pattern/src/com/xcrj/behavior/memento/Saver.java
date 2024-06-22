package com.xcrj.behavior.memento;

import java.util.Stack;

public class Saver {
    private Stack<Memento> ms = new Stack<>();

    public void save(Memento m) {
        ms.push(m);
    }

    public Memento get() {
        return ms.pop();
    }
}
