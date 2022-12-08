package com.wy.idempotent.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * todo desc
 *
 * @author 王渔
 * @date 2022/12/8 14:34
 */
@Component
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public boolean setEx(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean remove(String key) {
        if (!exist(key)) return false;
        return redisTemplate.delete(key);
    }
}
