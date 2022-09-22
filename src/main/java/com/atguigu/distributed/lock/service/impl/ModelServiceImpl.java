package com.atguigu.distributed.lock.service.impl;

import com.atguigu.distributed.lock.annotation.aop.Sync2Gitee;
import com.atguigu.distributed.lock.pojo.Stock;
import com.atguigu.distributed.lock.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ModelServiceImpl implements ModelService {

    @Override
    @Sync2Gitee
    public void normalSave(Stock stock, String message) {
        log.info("新建项目");
    }
}
