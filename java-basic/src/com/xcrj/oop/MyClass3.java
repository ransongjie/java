package com.xcrj.oop;
/**
 * 静态内部类
 */
public class MyClass3 {
    private static int age;
    private String name;
    public String getName() {
        return this.name;
    }
    public void SetName(String name){
        this.name=name;
    }

    static class InnerMyClass3{
        private String name;
        public String getName() {
            return this.name;
        }
        public void SetName(String name){
            this.name=name;
        }
        //静态内部类可以直接访问外部类的私有静态属性
        public void setAge(int age) {
            MyClass3.age=age;
        }
        public int getAge() {
            return MyClass3.age;
        }
    }

    public static void main(String[] args) {
        MyClass3 myClass3=new MyClass3();
        myClass3.SetName("I am outer class name");
        System.out.println(myClass3.getName());

        MyClass3.InnerMyClass3 innerMyClass3=new MyClass3.InnerMyClass3();
        innerMyClass3.SetName("I am inner class Name");
        System.out.println(innerMyClass3.getName());
        innerMyClass3.setAge(20);
        System.out.println(innerMyClass3.getAge());
    }
}
