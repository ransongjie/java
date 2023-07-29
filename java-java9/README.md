# java 9 module
- 从java 9 开始引入
- 本例使用idea测试

# module-info.java
- 在src目录下

exports, requires
- module1/src/module-info.java
```java
# 导出 包
exports com.xcrj01;
```
- module2/src/module-info.java
```java
# 引入 模块, 使用引入模块下的类
requires module1;
```

provides, uses
- module1/src/module-info.java
```java
// 接口的服务提供类
provides Apple with YanTaiApple;
```
- module2/src/module-info.java
```java
// 使用服务
uses Apple;
```