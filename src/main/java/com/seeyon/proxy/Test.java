package com.seeyon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by yangyu on 16/10/19.
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 创建一个Bean对象，该对象实现BeanInterFace接口
         */
        BeanInterFace bean = new Bean();

        /**
         * 创建一个MyProxy对象，该对象实现InvocationHandler接口，将bean注入到MyProxy中
         */
        InvocationHandler invocationHandler = new MyProxy(bean);

        /**
         * 调用JDK的Proxy类，使用newProxyInstance方法，直接生成Bean对象的代理对象
         * 注意：Classloader必须采用实际代理对象的ClassLoader，否则会出现反复调用的问题
         */
        BeanInterFace beanProxy = (BeanInterFace) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),invocationHandler);

        /**
         * 使用代理对象
         */
        System.out.println(beanProxy.getClass().getName());
        beanProxy.say();
    }
}
