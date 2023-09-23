package com.xcrj.behavior.command.example2;

public class ShootTank extends Cmd{
    ShootTank(Soldier executor){
        super(executor);
    }

    @Override
    public void execute() {
        executor.shootTank();
    }
    
}
