package com.seeyon.SpringBean.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangyu on 2017/3/30.
 */
public class TestFactoryBean {

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("/ioc/ioc.xml");
        Object o = appContext.getBean("myFactoryBean");
        System.out.println(o);
    }
}
