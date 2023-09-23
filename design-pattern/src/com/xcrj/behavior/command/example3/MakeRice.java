package com.xcrj.behavior.command.example3;

// 具体命令 制作米饭
public class MakeRice extends Command{
    // 传入命令执行者
    public MakeRice(Chef receiver){
        super(receiver);
    }

    // 执行命令
    @Override
    void execute() {
        receiver.makeRice();
    }
}
