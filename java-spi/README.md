# SPI (Service Provider Interface)
- 服务扩展点，扩展性
- Java提供的一套用来被第三方实现或者扩展的接口
- Java提供接口，第三方负责自己的实现
# API (Application Programming Interface)
- 服务功能点，功能性
# 过程
1. API 
2. SPI 通过ServiceLoader加载实现类并调用服务
3. Impl
# 样例
- 国家规定了标准的电动车充电接口
- 理想有自己的充电实现
- 蔚来有自己的充电实现
# Java SPI
- JDBC的驱动加载
- mysql-connector-java-xxx.jar resources/META-INF/services 目录下
- 文件名 接口 java.sql.Driver
- 文件内容 接口实现 com.mysql.jdbc.Driver
# SpringBoot SPI
- 自定义自动配置类
- resources/META-INF/spring
- 文件名  `org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- 文件内容 自动配置类xxxConfiguration全类名
# Dubbo SPI
- Dubbo提供了非常多的SPI，把SPI用到了极致
- resources/META-INF/dubbo
- 优化了Java SPI
    - JDK 标准的 SPI 会一次性实例化扩展点所有实现，如果有扩展实现初始化很耗时，但如果没用上也加载，会很浪费资源。
    - 如果扩展点加载失败，连扩展点的名称都拿不到了。
# 注意
- idea下java目录 mark as sources root
- idea下resources目录 mark as resources root