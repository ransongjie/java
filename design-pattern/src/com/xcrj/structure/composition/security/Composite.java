package com.xcrj.structure.composition.security;

import java.util.ArrayList;

public class Composite implements Component {

    private ArrayList<Component> children = new ArrayList<Component>();

    public void add(Component c) {
        children.add(c);
    }

    public void remove(Component c) {
        children.remove(c);
    }

    public Component getChild(int i) {
        return children.get(i);
    }
    /**
     * 递归方法
     * 如果是Composite类实例继续递归
     * 如果是Leaf类实例输出叶子信息
     */
    @Override
    public void operation() {
        for (Component c : children) {
            c.operation();
        }
    }
}
