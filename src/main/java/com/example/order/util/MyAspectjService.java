package com.example.order.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class MyAspectjService {
    @Before(value = "execution(* com.example.order.service.OrderService.*(..))")
    public void Before(JoinPoint joinPoint){
        System.out.println("Before : "+joinPoint.getSignature().getName());
    }
    @After(value = "execution(* com.example.order.service.OrderService.*(..))")
    public void After(JoinPoint joinPoint){
        System.out.println("After : "+ Arrays.toString(joinPoint.getArgs()));
    }
}
