# 测试Spring boot 最大连接数
application.yml
- `tomcat.threads.max-connections=30`
- `tomcat.threads.accept-count=10`
- 30+10=40

JMeter
- `setUp线程组`100个线程发送
- `HTTP请求` 连接超时时间200ms
- `汇总报告` 查看失败情况 结果40%失败率
