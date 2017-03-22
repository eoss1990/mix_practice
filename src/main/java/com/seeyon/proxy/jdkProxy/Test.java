package com.seeyon.proxy.jdkProxy;

import com.seeyon.proxy.Bean;
import com.seeyon.proxy.BeanInterFace;

/**
 * Created by yangyu on 2017/2/8.
 */
public class Test {
    public static void main(String[] args) {
        BeanInterFace beanProxy = new JdkDynamicProxy(new Bean()).getProxy();
        beanProxy.say();
    }
}
