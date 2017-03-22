package com.seeyon.SpringBean;

import com.seeyon.SpringBean.customerEvent.CustomerEventPublisher;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by yangyu on 16/10/10.
 */
public class test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("myBeans.xml");

        //factory.start();//触发ContextStartedEvent事件

        CustomerEventPublisher customerEventPublisher = (CustomerEventPublisher) factory.getBean("customerEventPublisher");
        customerEventPublisher.publish();

//        BeanSelf bs = (BeanSelf) factory.getBean("beanSelf");
//        factory.registerShutdownHook();
    }

    @Test
    public void test() {

        Set set = new HashSet();
        set.add("yangyu");
        set.add("yangyu");
    }

    @Test
    public void testLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("yangyu");
    }
}
