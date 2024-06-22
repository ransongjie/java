package com.xcrj.behavior.mediator;

// 中介者
public interface Mediator {
    // 作为经理和员工之间的沟通桥梁
    void setManager(Colleague colleague);

    void setEmployee(Colleague colleague);

    // 中介者转达谁的信息
    void transfer(Colleague colleague, String msg);
}