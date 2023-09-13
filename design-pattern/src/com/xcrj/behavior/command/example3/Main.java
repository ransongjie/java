package com.xcrj.behavior.command.example3;

public class Main {
    public static void main(String[] args) {
        // 创建命令执行者/接收者
        Chef receiver =new Chef();

        // 创建具体命令
        Command makeRice=new MakeRice(receiver);
        Command makeNoodle=new MakeNoodle(receiver);

        // 创建命令下达者/调用者
        Customer customer=new Customer();

        // 设置命令
        customer.setCmd(makeNoodle);
        customer.setCmd(makeNoodle);
        customer.setCmd(makeNoodle);
        customer.setCmd(makeRice);
        customer.setCmd(makeRice);

        // 删除命令
        customer.delCmd(makeNoodle);

        // 下达命令
        customer.callCmds();
    }
}
