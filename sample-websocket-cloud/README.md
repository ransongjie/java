# websocket-cloud
技术
- websocket stomp

测试
- gateway 对 websocket服务的负载均衡 

测试结果发现
- websocket服务的负载均衡，点击一次连接 负载到一个服务，后面的通信都在这个服务上

模块
- nginx
- gateway
- websocket
- webhttp

# 1个服务n个实例
IDEA
- Edit Configurations
- Allow parallel run
- VM options `-Dserver.port=9096`

# 使用
测试 连接websocket服务
1. nginx.conf 启动nginx
3. 启动项目 websocket(2个实例) gateway webhttp(2个实例)
4. index.html
5. 连接按钮
6. 输入消息 点击发送按钮 广播按钮

测试 连接webhttp服务
1. 打开浏览器 输入 `http://localhost:9091/one/xcrj/name` 响应 xcrj

# 建立连接过程
```
websocket
    index.html
    ws://localhost:9091/one/ws/stomp
    nginx.conf
    http://localhost:9092/two/ws/stomp
    gateway
    ws://websocket/stomp
    websocket
webhttp
    Browser GET
    http://localhost:9091/one/xcrj/name
    nginx.conf
    http://localhost:9092/two/xcrj/name
    gateway
    http://webhttp/xcrj/name
    webhttp
```

