package com.atguigu.distributed.lock.service;

import com.atguigu.distributed.lock.pojo.Stock;

public interface ModelService {

    public void normalSave(Stock stock, String message);

}
