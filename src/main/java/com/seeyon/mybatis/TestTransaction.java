package com.seeyon.mybatis;

import com.seeyon.mybatis.manager.PeopleManager;
import com.seeyon.mybatis.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangyu on 2017/2/10.
 */
public class TestTransaction {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mybatis/mybatis-spring.xml");
        PeopleManager peopleManager = (PeopleManager) context.getBean("peopleManagerImpl");

        People people = new People("1002","yangyu","chengdu","188888","nan");
        //没有事务
//        peopleManager.update(people);
//        peopleManager.insert(people);
        //有事务
        peopleManager.insertAndUpdate(people);
        System.out.println(11111);
    }
}
