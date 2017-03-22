package com.seeyon.proxy.jdkProxy;

import com.seeyon.proxy.Bean;
import com.seeyon.proxy.BeanInterFace;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangyu on 2017/2/8.
 */
public class JdkDynamicProxy implements InvocationHandler {

    private Object src;

    public JdkDynamicProxy(){}

    public JdkDynamicProxy(Object src){
        this.src = src;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
//        System.out.println(method.getName());
        Object object = method.invoke(src,args);
        after();
        return object;
    }

    private void before(){
        System.out.println("this is before");
    }

    private void after(){
        System.out.println("this is after");
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(src.getClass().getClassLoader(),src.getClass().getInterfaces(),this);
    }
}
