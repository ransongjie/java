# websocket-gateway-nginx
- nginx
- gateway
- websocket
- webhttp

# 使用
测试 连接websocket服务
1. nginx.conf 启动nginx
3. 启动项目 websocket gateway webhttp
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
    ws://localhost:9093/stomp
    websocket
webhttp
    Browser GET
    http://localhost:9091/one/xcrj/name
    nginx.conf
    http://localhost:9092/two/xcrj/name
    gateway
    http://localhost:9094/xcrj/name
    webhttp
```