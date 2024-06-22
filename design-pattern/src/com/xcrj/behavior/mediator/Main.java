package com.xcrj.behavior.mediator;

/*
 同事：经理、员工
 中介：聊天中介
 经理和员工通过中介交谈
 javac -d ./cp -encoding UTF-8 *.java
 java -XX:+ShowCodeDetailsInExceptionMessages -cp ./cp com.xcrj.behavior.mediator.Main
 */
public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Employee employee = new Employee();
        ChatMediator chatMediator = new ChatMediator();

        manager.setMediator(chatMediator);
        employee.setMediator(chatMediator);
        chatMediator.setEmployee(employee);
        chatMediator.setManager(manager);

        manager.chat("这个需求提前1天完工");
        employee.chat("好的，经理");
    }
}
