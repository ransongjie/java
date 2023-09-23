package com.xcrj.behavior.command.example1.order;

import com.xcrj.behavior.command.example1.recipient.Stock;

public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
