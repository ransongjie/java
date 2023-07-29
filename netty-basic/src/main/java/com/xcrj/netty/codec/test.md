# 测试
tcp+protobuf
1. 编写Netty相关服务
2. 编写*.proto文件
3. 先根据参考文件配置好protoc环境
4. cmd进入netty-basic目录下运行如下命令
```
protoc.exe --java_out=src/main/java --proto_path=proto proto/Student.proto
```
# 参考
- https://blog.csdn.net/Aiden_yan/article/details/114271119