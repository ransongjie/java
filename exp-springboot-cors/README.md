# spring boot解决跨域的方式
推荐 Nginx方式
## jsonp

- 优点：浏览器支持广泛
- 缺点：只支持get请求，前后端耦合在callback参数上

## springboot
- 优点：只需要编写后端代码
- 缺点：IE浏览器>=IE10
- 过程：浏览器会在Header中附加内容
  - 复杂跨域请求，浏览器请求2次
  - 先发送option请求后端是否支持
  - 再发送实际请求

@CrossOrigin("来源域") 方法注解
- 一个接口
- 单个方法

WebMvcConfigurer 配置类
- 批量接口

CorsFilter 过滤器
- 所有接口

## Nginx
IP:端口1 》 IP:端口2
```
server{
    listen       8081;
    server_name  localhost;
    
    # 前端
	location / {
        root  html;
        index index.html index.htm;
        error_page 404 = index.html;
    }
    
    # 后端
    location /cors/ {
        proxy_pass http://localhost:8080/user/;
        proxy_set_header  Host $http_host;
        proxy_set_header  X-Real-IP  $remote_addr;
        proxy_set_header  REMOTE-HOST  $remote_addr;
        proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```