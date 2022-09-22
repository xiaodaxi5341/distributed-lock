package com.atguigu.distributed.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean("redisson")
    public RedissonClient getRedissonClient(){
        RedissonClient redissonClient = Redisson.create();
        return redissonClient;
    }

}
