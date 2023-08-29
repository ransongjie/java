# 提升spring boot启动速度

- 原因：spring boot 启动时需要加载各种Bean
- 解决：方法1,2。提升20%左右

## 方法1，所有的Bean懒加载

- Bean用到了才进行初始化
- 3.397s 》3.097s
- 问题，第一次 http 请求处理的时间变长；错误不会在应用启动时抛出，不利于早发现

```yaml
spring:
  main:
    lazy-initialization: true
```

## 方法2，Bean 索引 spring-context-indexer

- 3.397s 》3.041s
- spring 5 提供 spring-context-indexer功能
- 编译时创建静态候选列表，提高大型应用程序的启动性能
- 问题，兼容性不够，出现问题可以使用此选项忽略`spring.index.ignore`

使用

- pom.xml引入`spring-context-indexer`
- 主启动类打上`@Indexed`
- 编译打包时生成`target/classes/META-INF/spring.components`文件
- Spring Context 执行 ComponentScan 扫描时
    - CandidateComponentIndexLoader 加载`target/META-INF/spring.components`
    - CandidateComponentIndexLoader 转化为`CandidateComponentIndex`对象

```xml

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-indexer</artifactId>
    <!-- 子项目不继承此依赖 -->
    <optional>true</optional>
</dependency>
```

## spring boot 3 + jdk17 + GraalVM
- jdk17 更优秀的GC
- spring boot 3+GraalVM 提供native
- 提升200%~400%