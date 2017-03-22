package com.seeyon.SpringBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by yangyu on 16/10/10.
 */
public class BeanSelf implements BeanFactoryAware, BeanNameAware,BeanClassLoaderAware, InitializingBean, DisposableBean {

    private String beanName;

    private BeanFactory beanFactory;

    private String name;

    public BeanSelf(){
        System.out.println("BeanSelf构造函数");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("这是BeanFactoryAware.setBeanFactory方法：" + beanFactory.getClass().getName().toString());
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("这是BeanNameAware.setBeanName方法：" + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("这是DisposableBean.destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("这是InitializingBean.afterPropertiesSet方法");
    }

    public void myInit(){
        System.out.println("调用<bean>的init-method方法");
    }

    public void mydestory(){
        System.out.println("调用<bean>的destroy-method方法");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Beanself属性注入");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("这是BeanClassLoaderAware.setBeanClassLoader方法：" + classLoader.getClass().getName().toString());
    }
}
