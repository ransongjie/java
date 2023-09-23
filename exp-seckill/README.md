# 秒杀方案
```text
lua脚本
if 商品数量>0:
   商品数量-1
   加入抢到商品的用户
   return 1;//代表成功
else
   return 0;//代表失败
```

# test "/seckill"
```text
jmeter
- `setUp线程组`100个线程发送
- `HTTP请求`

控制台打印，秒杀商品数量<0
```

# test "/seckill_lua"
```text
jmeter
- `setUp线程组`100个线程发送
- `HTTP请求`

控制台打印，秒杀商品数量不会<0
```