package com.seeyon.SpringBean.ioc;

import com.seeyon.SpringBean.aop.People;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by yangyu on 2017/3/30.
 * 使用FactoryBean，可以封装一些复杂对象的构建工作
 */
public class MyFactoryBean implements FactoryBean<People> {

    @Override
    public People getObject() throws Exception {
        return new People();
    }

    @Override
    public Class<?> getObjectType() {
        return People.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
