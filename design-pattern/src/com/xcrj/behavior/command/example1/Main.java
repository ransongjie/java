package com.xcrj.behavior.command.example1;

import com.xcrj.behavior.command.example1.caller.Broker;
import com.xcrj.behavior.command.example1.order.BuyStock;
import com.xcrj.behavior.command.example1.order.SellStock;
import com.xcrj.behavior.command.example1.recipient.Stock;

/**
 * 命令接收者
 * 命令对象
 * 命令调用者
 */
public class Main {
    public static void main(String[] args) {
        // 构建命令接收者
        Stock abcStock = new Stock();
        // 构建命令对象，传入命令接收者
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);
        // 构建命令调用者，持有命令，发送命令
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrders();
    }
}
