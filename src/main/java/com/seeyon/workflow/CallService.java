package com.seeyon.workflow;

import org.dom4j.DocumentHelper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangyu on 16/11/1.
 */
public class CallService {

    private static ClassPathXmlApplicationContext factory;

    static {
        factory = new ClassPathXmlApplicationContext("/workflow/spring_workflow.xml");
    }

    public static void main(String[] args) {
        ServiceFactory serviceFactory = (ServiceFactory) factory.getBean("serviceFactory");
        Service service = serviceFactory.getService("18615720100");
        service.workflow(DocumentHelper.createDocument());
        System.out.println("执行完成");
    }
}
