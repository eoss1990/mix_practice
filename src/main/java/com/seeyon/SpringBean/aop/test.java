package com.seeyon.SpringBean.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangyu on 16/10/12.
 */
public class test {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("SpringAop.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getClass());
        student.getName();
//        student.getAge();
//        student.printThrowException();
    }
}
