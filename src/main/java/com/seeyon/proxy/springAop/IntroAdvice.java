package com.seeyon.proxy.springAop;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by yangyu on 2017/2/9.
 */
@Component
public class IntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    @Override
    public void saySorry() {
        System.out.println("I am sorry!");
    }
}
