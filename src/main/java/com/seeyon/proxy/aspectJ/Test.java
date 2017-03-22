package com.seeyon.proxy.aspectJ;

import com.seeyon.proxy.Bean;
import com.seeyon.proxy.springAop.Apology;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangyu on 2017/2/9.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("AspectJ.xml");
        Bean bean = (Bean) applicationContext.getBean("bean");
        bean.say();
        bean.goodMorning();

        Apology apology = (Apology) bean;
        apology.saySorry();
    }
}
