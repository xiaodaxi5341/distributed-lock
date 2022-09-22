package com.atguigu.distributed.lock.mapper;

import com.atguigu.distributed.lock.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMapper extends BaseMapper<Stock> {
}
