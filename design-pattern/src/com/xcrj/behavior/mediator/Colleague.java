package com.xcrj.behavior.mediator;

// 同事
public interface Colleague {
    void setMediator(Mediator mediator);

    // 经理 和 员工 开始交谈
    void chat(String msg);

    // 通知聊天消息
    void info(String msg);
}
