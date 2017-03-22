package com.seeyon.proxy.aspectJ;

import com.seeyon.proxy.springAop.Apology;
import com.seeyon.proxy.springAop.IntroAdvice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by yangyu on 2017/2/9.
 */
@Component
@Aspect
public class MyAspect {


    /**
     * 引入增强
     * value:需要增强的目标类
     * Apology：增强接口
     * defaultImpl:增强接口的默认实现类
     */
    @DeclareParents(value = "com.seeyon.proxy.Bean",defaultImpl = IntroAdvice.class)
    private Apology apology;

    /**
     * 使用正则表达式匹配
     * @param pjp
     * @throws Throwable
     */
    @Around("execution(* com.seeyon.proxy.Bean.say(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        pjp.proceed();
        System.out.println("this is around");
    }

    /**
     * 使用注解匹配
     */
    @Before("@annotation(com.seeyon.proxy.aspectJ.Tag)")
    public void before(){
        System.out.println("this is before by annotation");
    }
}
