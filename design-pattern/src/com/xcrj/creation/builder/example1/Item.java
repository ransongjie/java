package com.xcrj.creation.builder.example1;

import com.xcrj.creation.builder.example1.packing.Packing;

/**
 * 食物条目和食物包装
 */
public interface Item {
    /**
     * 商品名称
     */
    public String name();

    /**
     * 商品打包
     */
    public Packing packing();

    /**
     * 商品价格
     */
    public float price();
}
