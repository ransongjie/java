package com.xcrj.behavior.command.example2;

public class ShootPlane extends Cmd{
    ShootPlane(Soldier executor){
        super(executor);
    }

    @Override
    public void execute() {
        executor.shootPlane();
    }
    
}
