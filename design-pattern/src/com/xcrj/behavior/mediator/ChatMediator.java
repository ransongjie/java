package com.xcrj.behavior.mediator;

// 交谈中介者 秘书
public class ChatMediator implements Mediator {
    private Employee employee;
    private Manager manager;

    @Override
    public void setManager(Colleague colleague) {
        manager = (Manager) colleague;
    }

    @Override
    public void setEmployee(Colleague colleague) {
        employee = (Employee) colleague;
    }

    @Override
    public void transfer(Colleague colleague, String msg) {
        if (employee == colleague) {
            manager.info(msg);
        } else if (manager == colleague) {
            employee.info(msg);
        }
    }

}
