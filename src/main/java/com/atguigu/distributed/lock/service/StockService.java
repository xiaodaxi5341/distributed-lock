package com.atguigu.distributed.lock.service;

import com.atguigu.distributed.lock.mapper.StockMapper;
import com.atguigu.distributed.lock.pojo.Stock;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class StockService {

//    private Stock stock = new Stock();

    @Autowired
    private StockMapper stockMapper;

    private ReentrantLock lock = new ReentrantLock();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private RedissonClient redisson;

    public void deduct(){
        RLock lock = redisson.getLock("lock");
        lock.lock();

        try{
            String s = redisTemplate.opsForValue().get("stock");
            if (s != null && s.length() != 0) {
                    int st = Integer.parseInt(s);
                if (st > 0) {
                    redisTemplate.opsForValue().set("stock", String.valueOf(--st));
                }
            }
        }finally {
            lock.unlock();
        }

    }

//    @Transactional
//    public void deduct() {
//        lock.lock();
//        try {
//            Stock stock = this.stockMapper.selectOne(new QueryWrapper<Stock>().eq("product_code", "1001"));
//            if (stock != null && stock.getCount() > 0) {
//                stock.setCount(stock.getCount() - 1);
//                this.stockMapper.updateById(stock);
//                log.info("current port : {}",System.getProperty("server.port"));
//            }
//        } finally {
//            lock.unlock();
//        }
//    }

//    public void deduct() {
//
//        String uuid = UUID.randomUUID().toString();
//
//        Boolean stock = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
//
//        if (!stock) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            this.deduct();
//        } else {
//            try {
//                String s = redisTemplate.opsForValue().get("stock");
//                if (s != null && s.length() != 0) {
//                    int st = Integer.parseInt(s);
//                    if (st > 0) {
//                        redisTemplate.opsForValue().set("stock", String.valueOf(--st));
//                    }
//                }
//            } finally {
//                //此处非原子性
////                if (uuid.equals(redisTemplate.opsForValue().get("lock")))
////                    redisTemplate.delete("lock");
//                //通过lua脚本
//                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
//                redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList("lock"),uuid);
//            }
//        }
//    }


}
