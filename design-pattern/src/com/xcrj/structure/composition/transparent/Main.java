package com.xcrj.structure.composition.transparent;

/**
 * 透明方式：在接口和实现类中都加入了增删改查的方法
 */
public class Main {
    public static void main(String[] args) {
        Component composite0 = new Composite();
        Component composite1 = new Composite();
        Component leaf1 = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");
        composite0.add(leaf1);
        composite0.add(composite1);
        composite1.add(leaf2);
        composite1.add(leaf3);

        composite0.operation();
    }
}
