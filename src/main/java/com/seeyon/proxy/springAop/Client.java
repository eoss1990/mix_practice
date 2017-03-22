package com.seeyon.proxy.springAop;

import com.seeyon.proxy.Bean;
import com.seeyon.proxy.BeanInterFace;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 编程式实现Spring Aop
 * Created by yangyu on 2017/2/8.
 */
public class Client {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Bean());

        //前置，后置增强
//        proxyFactory.addAdvice(new BeforeAdvice());
//        proxyFactory.addAdvice(new AfterAdvice());

        //环绕增强
        proxyFactory.addAdvice(new AroundAdvice());

        BeanInterFace beanProxy = (BeanInterFace) proxyFactory.getProxy();
        beanProxy.say();
    }
}
