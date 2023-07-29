package com.xcrj.spi;
//小鹏充电器
public class XiaoPengCharger implements StandardCharger{

    @Override
    public String name() {
       return "我是 XiaoPengCharger";
    }
    
}
