package com.seeyon.proxy.springAop;

import com.seeyon.proxy.Bean;
import com.seeyon.proxy.BeanInterFace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 采用xml配置方式，使用springAop，xml配置请看Aop.xml
 * Created by yangyu on 2017/2/8.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Aop.xml");
        BeanInterFace beanProxy = (BeanInterFace) applicationContext.getBean("bean");
        beanProxy.say();

//        Bean bean = (Bean) applicationContext.getBean("introProxy");
//        bean.say();
//        Apology apology = (Apology) bean;
//        apology.saySorry();

//        Bean bean = (Bean) applicationContext.getBean("beanAdvisorProxy");
//        bean.say();
//        bean.goodMorning();
//        bean.goodNight();
    }
}
