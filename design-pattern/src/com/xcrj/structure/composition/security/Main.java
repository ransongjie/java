package com.xcrj.structure.composition.security;

/**
 * 安全方式：只是在组合类中有增删改查方法，接口只是提取了公共方法
 */
public class Main {
    public static void main(String[] args) {
        //实现类中才有 增删查方法
        Composite composite0 = new com.xcrj.structure.composition.security.Composite();
        Composite composite1 = new com.xcrj.structure.composition.security.Composite();
        Component leaf1 = new com.xcrj.structure.composition.security.Leaf("1");
        Component leaf2 = new com.xcrj.structure.composition.security.Leaf("2");
        Component leaf3 = new com.xcrj.structure.composition.security.Leaf("3");
        composite0.add(leaf1);
        composite0.add(composite1);
        composite1.add(leaf2);
        composite1.add(leaf3);

        composite0.operation();
    }
}
