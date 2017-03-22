package com.seeyon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangyu on 16/10/19.
 */
public class MyProxy implements InvocationHandler {

    private BeanInterFace bean;

    public MyProxy(BeanInterFace bean){
        this.bean=bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        method.invoke(bean,args);
        System.out.println("after");
        return null;
    }
}
