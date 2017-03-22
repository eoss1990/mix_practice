package com.seeyon.proxy.springAop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * Created by yangyu on 2017/2/8.
 */
@Component
public class AroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object object = methodInvocation.proceed();
        after();
        return object;
    }

    private void before(){
        System.out.println("this is around-before");
    }

    private void after(){
        System.out.println("this is around-after");
    }
}
