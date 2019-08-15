package com.example.demo.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @CLASSNAME RedisLock
 * @DESCRIPTION 描述
 * @AUTHOR chen
 * @DATE 2019-06-25 21:11
 **/
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String k, String v) {
        if (redisTemplate.opsForValue().setIfAbsent( k, v )) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get( k );
        //如果锁过期
        if (!StringUtils.isEmpty( currentValue ) && Long.parseLong( v ) < System.currentTimeMillis()) {
            //获取上一个锁时间
            String oldValue = redisTemplate.opsForValue().getAndSet( k, v );
            if (!StringUtils.isEmpty( oldValue ) && oldValue.equals( currentValue )) {
                return true;
            }
        }
        return false;
    }

    public void unLock(String k, String v) {
        try {
            String currentValue = redisTemplate.opsForValue().get( k );
            if (!StringUtils.isEmpty( currentValue ) && currentValue.equals( v )) {
                redisTemplate.opsForValue().getOperations().delete( k );
            }
        } catch (Exception e) {
            log.error( "redis分布式锁,解锁异常:{}",e );
        }
    }
}
