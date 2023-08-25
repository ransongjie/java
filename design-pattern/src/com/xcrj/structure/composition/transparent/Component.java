package com.xcrj.structure.composition.transparent;

public interface Component {
    //增加
    public void add(Component c);
    //删除
    public void remove(Component c);
    //获取子节点
    public Component getChild(int i);
    //操作
    public void operation();
}
