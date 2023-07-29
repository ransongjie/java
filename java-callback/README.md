# 接口回调
A调用B去做事情，B做完事情后通知A
- CallBackInterface callBackFunc()
- A类 implements CallBackInterface, 调用B.doSomething()
- B类 doSomething(){执行完成调用callBackFunc()}
- 领导让下属做工作，下属做完工作之后通知领导
- 回调，完成任务后通知