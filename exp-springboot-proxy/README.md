# 测试不同代理

## 静态代理

实现：

- 代理类和目标类都需要实现同一个接口
- 代理类 持有目标类+调用目标方法+功能增强

缺点：

- 手动编写代理类
- 不能动态（运行时）改变代理类和目标类的关系

## jdk动态代理

- 基于接口，运行时生成代理类
- 公共接口，目标类，InvocationHandler/invoke() 持有目标类+调用目标方法+功能增强

## cglib动态代理

- 基于继承，运行时生成代理类
- 目标类，MethodInterceptor/intercept()调用父类目标方法+功能增强，Enhancer创建代理类

# 测试不同jdk版本动态代理

## jdk7

## jdk8

## jdk11

## jdk17

## jdk21