package com.wab.redis;

import com.wab.base.SpringBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author hcq
 * @create 2018-02-08 下午 5:30
 **/

public class RedisTest extends SpringBase {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void Test() throws Exception {
        String key = "a_tets";
        String value = "1231231";
        stringRedisTemplate.opsForValue().set(key, value);
        String s = stringRedisTemplate.opsForValue().get(key);
        System.out.println(s);
        System.out.println(213);
    }
}
