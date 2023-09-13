package com.xcrj.behavior.command.example3;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令下达者/调用者
 */
class Customer {
    private List<Command> cmds = new ArrayList<>();

    // 设置订单/命令
    public void setCmd(Command cmd) {
        cmds.add(cmd);
    }

    // 取消订单/命令
    public void delCmd(Command cmd) {
        cmds.remove(cmd);
    }

    // 执行命令
    public void callCmds() {
        for (Command cmd : cmds) {
            cmd.execute();
        }
    }
}