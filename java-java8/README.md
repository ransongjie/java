# 总述
- func_interface: 函数式接口
- lambda: lambda表达式
- func_reference: 方法引用
- stream: 流式编程
- interface_default_func: 接口默认方法
- datetime: 日期时间
- base64: base64编码解码
- optional: null处理

# 函数式编程
方法作为入参，函数式接口 作为方法入参，lambda表达式作函数式接口中抽象方法的方法体
func_interface: 函数式接口，interface上标注@FunctionalInterface
lambda: lambda表达式，作为 函数式接口 中 抽象方法 的方法体
func_reference: 方法引用

函数式接口，lambda表达式，lambda表达式所在类
- lambda表达式是 函数式接口 的实现类
- lambda表达式是 lambda表达式所在类 的内部类
- lambda表达式等号右边是 函数式接口中的 抽象方法 的实现
- 传入lambda表达式的变量 必须是final或effectively final

函数式接口，lambda表达式，方法引用
- 方法引用，引用方法
- 引用已有方法 避免自己写lambda表达式，使得代码更加紧凑
- 方法引用，双冒号::
- 引用方法的方法体 和 函数式接口中的抽象方法 要一致
# 流式编程
先打散（stream()/parallelStream()）
映射map()，
过滤filter()，
收集collect()，
sql中能有的操作，流式编程基本都有

# 接口优化，默认方法
- 为什么出现？接口增加抽象方法，修改抽象方法之后，所有的实现类都需要修改，破坏了向下兼容
- 类 implements接口，可以`@Override default`方法
- 类 extends类 implements接口，优先调用继承类的方法，类优先
- 类 implements接口1,接口2，必须`@Override 相同的default方法`
# 日期时间优化
LocalDateTime:
- LocalDateTime 获取当前时间
- LocalDateTime 创建时间
- Loca- lDateTime 格式化 String
- LocalDateTime 转 timestamp 系统时区
- LocalDateTime 转 timestamp 东8区
- timestamp 转 LocalDateTime
- Date 转 LocalDateTime
- LocalDateTime 转 Date
- LocalDateTime 加减
- LocalDateTime 对比
# base64
|Name|Function|
|---|---|
|`Base64.getEncoder()/getDecoder()`|RFC4648 Encoder and Decoder|
|`Base64.getUrlEncoder()/getUrlDecoder()`|RFC4648_URLSAFE  UrlEncoder and UrlDecoder|
|`Base64.getMimeEncoder()/getMimeDecoder()`|RFC2045 MimeEncoder and MimeDecoder|
|`Base64.getMimeEncoder(int lineLength, byte[] lineSeparator)/getMimeDecoder()`|RFC2045 MimeEncoder and MimeDecoder|

# Optional
- 与流式编程结合, 处理 `java.lang.NullPointerException`
- 常用 `get()`, `isPresent()`, `ifPresent()`