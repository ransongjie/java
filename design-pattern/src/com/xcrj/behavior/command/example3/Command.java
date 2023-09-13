package com.xcrj.behavior.command.example3;

public abstract class Command {
    protected Chef receiver;
    // 设置命令执行者
    public Command(Chef receiver){
        this.receiver=receiver;
    }

    // 执行命令
    abstract void execute();
}
