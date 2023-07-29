package com.xcrj.oop;

public abstract class AbstractClass1 {
    private String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public abstract void getSecondName();
    public void getNickName() {
        System.out.println("I am xcrj nick name");
    }
}
