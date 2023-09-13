package com.xcrj.behavior.command.example2;

import java.util.ArrayList;
import java.util.List;
/**
 * 将军 负责下达命令
 */
public class Admiral {
    private List<Cmd> cmds=new ArrayList<>();

    // 添加命令
    public void addCmd(Cmd cmd){
        cmds.add(cmd);
    }

    // 删除命令
    public void delCmd(Cmd cmd){
        cmds.remove(cmd);
    }

    // 下达命令
    public void makeCmds(){
        for (Cmd cmd : cmds) {
            cmd.execute();
        }
    }
}
