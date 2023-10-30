package com.xcrj.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) throws Exception {
        test5();
    }

    /**
     * 创建对象
     *
     * @throws Exception
     */
    private static void test5() throws Exception {
        // 1. class.newInstance()创建对象
        Class<Dog> c = Dog.class;
        Dog d = c.newInstance();// 调用了无参构造函数
        // setter设置属性
        Field fName = c.getDeclaredField("name");
        fName.setAccessible(true);
        fName.set(d, "旺财");
        Field fage = c.getDeclaredField("age");
        fage.setAccessible(true);
        fage.set(d, 20);
        System.out.println(d.toString()); //Dog{name='旺财', age=20}

        // 2. 有参构造创建对象
        Class<Dog> c2 = Dog.class;
        // 获取指定构造函数
        Constructor<Dog> constructor = c2.getDeclaredConstructor(String.class, int.class);
        // 设置可达性
        constructor.setAccessible(true);
        Dog d2 = constructor.newInstance("旺财", 2);
        System.out.println(d2.toString());// Dog{name='旺财', age=2}
    }

    /**
     * 子类通过反射获取父类属性
     *
     * @throws Exception
     */
    private static void test4() throws Exception {
        Class c1 = Class.forName("com.xcrj.reflect.Son");
        //获取父类私有属性值
        Object obj = c1.newInstance();
        System.out.println(getFieldValue(obj, "privateField"));
    }

    public static Object getFieldValue(Object object, String fieldName) throws Exception {
        Field field = getDeclaredField(object, fieldName);
        return field.get(object);
    }

    public static Field getDeclaredField(Object obj, String fieldName) {
        Field field = null;
        // 入参是Object类型，使用这个方法获取class对象
        Class c = obj.getClass();
        // 一直往上找，截止Object
        for (; c != Object.class; c = c.getSuperclass()) {
            try {
                field = c.getDeclaredField(fieldName);
                field.setAccessible(true);// 可能是私有属性
                return field;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行c = c.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * Class对象方法操作
     */
    private static void test3() throws Exception {
        Class c2 = Person.class;

        //获得类完整的名字
        String className = c2.getName();
        System.out.print(className);//com.xcrj.reflect.Person
        System.out.println();

        //获得类的public类型的属性。
        Field[] fields = c2.getFields();
        for (Field field : fields) {
            System.out.print(field.getName() + " ");//age
        }
        System.out.println();

        //获得类的所有属性。包括private属性
        Field[] allFields = c2.getDeclaredFields();
        for (Field field : allFields) {
            System.out.print(field.getName() + " ");//name  age
        }
        System.out.println();

        //获得类的public类型的方法。这里包括 Object类的public方法
        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + " ");//work wait wait wait equals toString hashCode getClass notify notifyAll
        }
        System.out.println();

        //获得类的所有方法。
        Method[] allMethods = c2.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.print(method.getName() + " ");//say work
        }
        System.out.println();

        //获得指定的属性
        Field f1 = c2.getField("age");
        System.out.println(f1); // public int com.xcrj.reflect.Person.age

        //获得指定的私有属性
        Field f2 = c2.getDeclaredField("name");
        //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        f2.setAccessible(true);
        System.out.println(f2);// private java.lang.String com.xcrj.reflect.Person.name

        //获取构造方法
        Constructor[] constructors = c2.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.print(constructor.toString() + " ");//public com.xcrj.reflect.Person()
        }
        System.out.println();

        //创建这个类的一个对象
        Object p2 = c2.newInstance();
        //f2是私有属性
        f2.set(p2, "Bob");
        //使用反射机制可以打破封装性，导致了java对象的属性不安全。
        System.out.println(f2.get(p2)); //Bob
    }

    /**
     * 得到 Class 的三种方式
     *
     * @throws Exception
     */
    private static void test2() throws Exception {
        //1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
        //  类型的对象，而我不知道你具体是什么类，用这种方法
        Person person = new Person();
        Class c1 = person.getClass();
        System.out.println(c1);

        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        //  这说明任何一个类都有一个隐含的静态成员变量 class
        Class c2 = Person.class;
        System.out.println(c2);

        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //   但可能抛出 ClassNotFoundException 异常
        Class c3 = Class.forName("com.xcrj.reflect.Person");
        System.out.println(c3);
    }

    /**
     * 获取对象的属性名和值
     */
    private static void test1() throws Exception {
        Student xcrj = new Student();
        xcrj.setName("xcrj");
        Map<String, Object> nameValueMap = getObjFieldNameValue(xcrj);
        nameValueMap.forEach((k, v) -> {
            System.out.println(k + "= " + v);
        });
    }

    private static Map<String, Object> getObjFieldNameValue(Object obj) throws Exception {
        Class clazz = obj.getClass();
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            //通过fieldName获取getField()方法进而获取字段值
            String fieldName = field.getName();
            //PropertyDescriptor（jdk1.8）：要求setter返回值为void
            //lombok的@Accessors(chain = true)：链式调用注解，setter方法返回对象本身
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method getMethod = pd.getReadMethod();
            Object value = getMethod.invoke(obj);
            map.put(fieldName, value);
        }

        return map;
    }


}

