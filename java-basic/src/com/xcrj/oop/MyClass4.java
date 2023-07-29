package com.xcrj.oop;

/**
 * 非静态内部类
 */
public class MyClass4 {
    private int outerAge;
    private String name;

    public String getName() {
        return this.name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public class InnerMyClass4 {
        /**
         * 非静态内部类中不能使用静态属性
         * 类初始化先于对象初始化
         * 外部对象创建先于内部对象创建
         */
        // private static float salary=10.0f;
        /**
         * 非静态内部类中能使用静态常量属性 
         * 静态常量 在编译期就初始化
         */
        private static final float expectedSalary=10.0f;
        private String name;

        public String getName() {
            return this.name;
        }

        public void SetName(String name) {
            this.name = name;
        }

        //内部类可以直接访问外部类的私有属性
        public void setAge(int age) {
            outerAge=age;
        }
        public int getAge() {
            return outerAge;
        }
    }

    public static void main(String[] args) {
        MyClass3 myClass3=new MyClass3();
        myClass3.SetName("I am outer class name");
        System.out.println(myClass3.getName());

        MyClass4 myClass4=new MyClass4();
        MyClass4.InnerMyClass4 innerMyClass4=myClass4.new InnerMyClass4();
        innerMyClass4.SetName("I am xcrj");
        System.out.println(innerMyClass4.getName());
        innerMyClass4.setAge(20);
        System.out.println(innerMyClass4.getAge());
    }
}
