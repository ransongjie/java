package com.xcrj.behavior.command.example1.caller;

import java.util.ArrayList;
import java.util.List;

import com.xcrj.behavior.command.example1.order.Order;

public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    /**
     * * @author ransongjie * @create 2021/1/17 * @function 一条条的执行命令 * @param
     * * @return
     */
    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
