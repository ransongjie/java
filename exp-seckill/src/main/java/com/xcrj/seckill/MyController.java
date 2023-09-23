package com.xcrj.seckill;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@RestController
public class MyController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    // redis keys
    private static final String GOODS_KEY = "goods:1001";
    private static final String USER_KEY = "user:1001";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    // 启动时，添加秒杀商品
    @PostConstruct
    public void testPostConstruct() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer(Object.class));
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.opsForValue().set(GOODS_KEY, 10);
    }

    @GetMapping("/seckill")
    public String seckill() {
        //redis string
        ValueOperations<String, Object> goodNum = redisTemplate.opsForValue();
        //redis list
        ListOperations<String, Object> users = redisTemplate.opsForList();

        //随机值作用户id
        int userId = new Random().nextInt(1000);

        if (Integer.parseInt(goodNum.get(GOODS_KEY).toString()) > 0) {
            //商品数量-1
            goodNum.decrement(GOODS_KEY);
            //加入用户id
            users.leftPush(USER_KEY, String.valueOf(userId));
            //输出抢到商品的所有用户
            System.out.println(users.range(USER_KEY, 0, -1));
            //输出秒杀商品剩余数量
            System.out.println(goodNum.get(GOODS_KEY));
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    @GetMapping("/seckill_lua")
    public String seckillLua() {
        //随机值作用户id
        int userId = new Random().nextInt(1000);

        /**
         * 创建lua脚本
         * if 商品数量>0:
         *    商品数量-1
         *    加入抢到商品的用户
         *    return 1;//代表成功
         * else
         *    return 0;//代表失败
         */
        String script = "if tonumber(redis.call('get', KEYS[1]))>0 then redis.call('decr', KEYS[1]) redis.call('lpush', KEYS[2], ARGV[1]) return 1 else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);

        //redis keys
        List<String> keyList = new ArrayList<>();
        keyList.add(GOODS_KEY);
        keyList.add(USER_KEY);
        // param3 是 ARGV[1]
        Long result = (Long) redisTemplate.execute(redisScript, keyList, String.valueOf(userId));

        ValueOperations<String, Object> goodNum = redisTemplate.opsForValue();
        ListOperations<String, Object> users = redisTemplate.opsForList();

        // 秒杀成功 result=1 else result=0
        if (result == 1) {
            //商品数量
            System.out.println(goodNum.get(GOODS_KEY));
            //抢到商品的用户
            System.out.println(users.range(USER_KEY, 0, -1));
            return SUCCESS;
        } else {
            return FAIL;
        }
    }
}
