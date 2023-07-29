package com.xcrj.spi;
//蔚来充电器
public class NIOCharger implements StandardCharger{

    @Override
    public String name() {
        return "我是 NIOCharger";
    }
    
}
