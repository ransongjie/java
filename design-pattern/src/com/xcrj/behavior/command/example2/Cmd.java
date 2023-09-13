package com.xcrj.behavior.command.example2;

abstract class Cmd {
    protected Soldier executor;
    Cmd(Soldier executor){
        this.executor=executor;
    }
    abstract void execute();
}
