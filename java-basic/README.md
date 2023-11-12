# java-basic
|Package|Intro|
|---|---|
|data_type|数据类型|
|operator|运算符|
|control|for while控制结构|
|func|函数|
|oop|class, abstract class, (static) Inner class, interface|
|error|错误处理|
|concurrent|concurrent|
|generic|generic paradigm|
|io||
|net/bio|BIO|
|net/nio|NIO|
|reflect|reflect|
|dserialized|序列化/反序列化|
|test|test|

data_type
- 主要：防止溢出

generic
- 原理：编译期泛型检查和泛型擦除
- 使用：经常要分别为不同的类型写完全相同逻辑的代码，使用泛型将是最合适的选择
- 注意：任何泛型类型都必须传入类型实参 实例化后才可以使用

error
- 程序退出，正常退出 return，异常退出 throw 异常 未catch
- 异常处理，catch异常 让程序继续运行

dserialized
- 序列化: ByteArrayOutputStream, ObjectOutputStream
- 反序列化: ByteArrayInputStream, ObjectInputStream