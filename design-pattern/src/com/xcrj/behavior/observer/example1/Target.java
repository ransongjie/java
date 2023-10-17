package com.xcrj.behavior.observer.example1;

import java.util.ArrayList;
import java.util.List;

public class Target {
    // 状态
    private int state;
    // 观察者
    private List<Observer> observers = new ArrayList<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
