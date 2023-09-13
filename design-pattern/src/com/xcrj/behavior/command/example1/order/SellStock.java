package com.xcrj.behavior.command.example1.order;

import com.xcrj.behavior.command.example1.recipient.Stock;

public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
