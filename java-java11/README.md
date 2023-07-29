# 总述
- 完全支持容器
- ZGC
- HttpClient
- Module
- API 增强
- var 局部变量类型推断
- Jshell直接编译
- 简化编译运行程序
- unicode 10支持

# JShell
作用：
- bin/jshell
- 类似Python等脚本语言
- 方便测试

使用：
- 在命令行窗口中输入`jshell`
- 再直接写代码
- 退出jshell`/exit`

jshell命令
```sh
/help
```
# var 局部变量类型推断
类型推断
```
// 右边的<>根据左边的<Integer>进行类型推断
List<Integer> list=new ArrayList<>();
// 
var str = "xcrj";
//
var sb =new StringBuilder("xcrj");
//
var alist=new ArrayList<Integer>();
// var不是关键字
int var = 10;
```

注意：
- `var a;`, 必须写右边，否则不能进行推断
- `class A{ var a;}`, 类属性不支持，局部变量类型推断

应用
```
// lambda表达式是函数式接口Runnable的实现类
Thread t = new Thread(()->{System.out.println(Thread.currentThread().getName());});
t.start()

// lambda表达式是 lambda表达式所在类 的匿名内部类
Consumer<String> consumer=v->System.out.println(v.toUpperCase());
Consumer<String> consumer=(var v)->System.out.println(v.toUpperCase());
// 加了var之后可以加注解
Consumer<String> consumer=(@Deprecated var v)->System.out.println(v.toUpperCase());
Consumer<String> consumer=(@Nonnull var v)->System.out.println(v.toUpperCase());
Consumer<String> consumer=(@Nullable var v)->System.out.println(v.toUpperCase());
// 为什么可以直接调用
consumer.accept("xcrj");
```

# API 增强
- 集合API, of()静态方法创建集合, 创建的集合不能add()
- Stream API
- String API
- Optional API
- InputStream API

# HttpClient
- 见 HTTPClientMe

# 简化编译运行程序
- 只会运行.java文件中的第一个class, 第1个class应该包含main方法
- 所需所有class必须写入同一个.java
```sh
java SimpleCompileRun.java
```

# unicode 10支持
- unicode 10总计字符136690个字符，超过char表示范围
- 新增 CharacterData00, CharacterData01, CharacterData02, CharacterData0E 等
- 旧有 String的`int codePointAt(int index)`方法使用int表示char, 可以代替`char charAt(int index)`方法

# Module

# ZGC
