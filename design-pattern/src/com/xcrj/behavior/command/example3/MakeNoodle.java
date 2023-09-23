package com.xcrj.behavior.command.example3;

// 具体命令 制作面条
public class MakeNoodle extends Command{
    // 设置命令执行者
    public MakeNoodle(Chef receiver){
        super(receiver);
    }

    // 执行命令
    @Override
    void execute() {
        receiver.makeNoodle();
    }
    
}
