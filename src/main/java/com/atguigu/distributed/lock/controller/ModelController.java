package com.atguigu.distributed.lock.controller;

import com.atguigu.distributed.lock.pojo.Stock;
import com.atguigu.distributed.lock.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @RequestMapping("/program1")
    public String program1(Stock stock,  String message) {

        modelService.normalSave(stock, message);
        return "program1";

    }

}
