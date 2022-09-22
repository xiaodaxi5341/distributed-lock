package com.atguigu.distributed.lock.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class SyncGiteeAop {

    @Pointcut("@annotation(com.atguigu.distributed.lock.annotation.aop.Sync2Gitee)")
    public void pointcut(){

    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg->{
            log.info("class : {} , value : {}" , arg.getClass() , arg);
        });
    }

}
