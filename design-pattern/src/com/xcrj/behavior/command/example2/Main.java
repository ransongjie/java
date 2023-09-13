package com.xcrj.behavior.command.example2;

/**
 * 命令模式 分离 命令执行者 和 命令下达者
 * 类，执行者 命令 下达者
 */
public class Main {
    public static void main(String[] args) {
        // 创建命令执行者
        Soldier executor=new Soldier();

        // 创建命令
        Cmd shootPlane=new ShootPlane(executor);
        Cmd shootTank=new ShootTank(executor);
        
        // 创建命令调用者
        Admiral maker=new Admiral();

        // 添加命令
        maker.addCmd(shootPlane);
        maker.addCmd(shootPlane);
        maker.addCmd(shootPlane);
        maker.addCmd(shootTank);
        maker.addCmd(shootTank);

        // 删除命令
        maker.delCmd(shootPlane);

        // 执行命令
        maker.makeCmds();
    }
}
