package com.atguigu.distributed.lock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("db_stock")
public class Stock {

    private Long id;
    private String productCode;
    private String warehouse;
    private Integer count;
}
