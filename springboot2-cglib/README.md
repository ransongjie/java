# springboot-cglib
- spring boot 2.x 默认使用cglib动态代理

cglib效率更高，更灵活
- 原理：CGLIB动态代理基于字节码生成实现；JDK动态代理基于反射实现
- CGLIB非final类基于继承实现动态代理，JDK基于接口实现动态代理
- 代理类生成：CGLIB通过Enhancer类创建代理类（子类），JDK通过Proxy类和InvocationHandler接口创建代理对象
- 被代理方法调用过程：CGLIB通过FastClass机制直接调用目标方法，JDK通过InvocationHandler转发调用目标方法